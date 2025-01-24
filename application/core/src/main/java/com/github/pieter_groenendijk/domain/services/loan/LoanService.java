package com.github.pieter_groenendijk.domain.services.loan;

import com.github.pieter_groenendijk.domain.entities.loan.Loan;
import com.github.pieter_groenendijk.domain.entities.loan.LoanStatus;
import com.github.pieter_groenendijk.domain.entities.membership.Membership;
import com.github.pieter_groenendijk.domain.entities.membership.MembershipType;
import com.github.pieter_groenendijk.domain.entities.reservation.Reservation;
import com.github.pieter_groenendijk.domain.exception.EntityNotFoundException;
import com.github.pieter_groenendijk.shared.dto.loan.LoanDTO;
import com.github.pieter_groenendijk.datasource.repositories.loan.ILoanRepository;
import com.github.pieter_groenendijk.domain.entities.product.ProductCopy;
import com.github.pieter_groenendijk.domain.entities.product.ProductCopyStatus;
import com.github.pieter_groenendijk.domain.services.loan.event.ILoanEventService;

import static com.github.pieter_groenendijk.domain.shared.ServiceUtils.LOAN_LENGTH;
import com.github.pieter_groenendijk.datasource.repositories.membership.IMembershipRepository;
import com.github.pieter_groenendijk.datasource.repositories.membership.type.IMembershipTypeRepository;
import com.github.pieter_groenendijk.datasource.repositories.product.IProductRepository;
import com.github.pieter_groenendijk.domain.services.reservation.IReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService implements ILoanService {
    private final IReservationService reservationService;
    private final ILoanRepository loanRepository;
    private final IProductRepository productRepository;
    private final ILoanEventService EVENT_SERVICE;
    private final IMembershipRepository membershipRepository;
    private final IMembershipTypeRepository membershipTypeRepository;

    public LoanService(
        ILoanRepository loanRepository,
        IMembershipRepository membershipRepository,
        ILoanEventService eventService,
        IReservationService reservationService,
        IProductRepository productRepository,
        IMembershipTypeRepository membershipTypeRepository
    ) {
        this.loanRepository = loanRepository;
        this.membershipRepository = membershipRepository;
        this.EVENT_SERVICE = eventService;
        this.reservationService = reservationService;
        this.productRepository = productRepository;
        this.membershipTypeRepository = membershipTypeRepository;
    }

    // TODO: Implement correct error handling. Is a loan still successful if we failed to schedule events for it, or the other way around?
    @Override
    public Loan store(LoanDTO loanDTO) throws Exception {
        if (loanDTO == null) {
            throw new IllegalArgumentException("LoanRequestDTO cannot be null.");
        }
        validateLoanRequestDTO(loanDTO);

        Loan loan = new Loan();
        loan.setLoanStatus(LoanStatus.ACTIVE);
        setLoanDates(loan);


        Membership membership = membershipRepository.retrieveMembershipById(loanDTO.getMembershipId())
                .orElseThrow(() -> new EntityNotFoundException("Membership not found"));
        loan.setMembership(membership);
        checkIfAccountIsBlocked(membership);
        ProductCopy productCopy = productRepository.retrieveProductCopyById(loanDTO.getProductCopyId())
                .orElseThrow(() -> new EntityNotFoundException("ProductCopy not found"));
        loan.setProductCopy(productCopy);

        checkDoesLoanExceedLimitForMembership(membership);
        checkDoesLoanExceedLimitForGenre(membership, productCopy);

        productCopy.setAvailabilityStatus(ProductCopyStatus.LOANED);
        productRepository.updateProductCopy(productCopy);

        loanRepository.store(loan);
        EVENT_SERVICE.handleEventsForNewLoan(loan);

        return loan;
    }

    private void checkIfAccountIsBlocked(Membership membership) throws Exception {
        if (membership.isBlocked()) {
            throw new IllegalStateException("The account is blocked and cannot make a loan.");
        }
    }

    @Override
    public void extendLoan(long loanId, LocalDate returnBy) {
        Loan loan = loanRepository.retrieveLoanByLoanId(loanId);
        if (loan == null) {
            throw new IllegalArgumentException("Loan not found with id: " + loanId);
        }

        if (loan.getLoanStatus().equals(LoanStatus.EXTENDED)) {
            throw new IllegalStateException("Loan can only be extended once.");
        }

        LocalDate extendedReturnBy = generateReturnByDate(loan.getReturnBy());

        loan.setReturnBy(extendedReturnBy);
        loan.setLoanStatus(LoanStatus.EXTENDED);

    }

    @Override
  public LocalDate generateReturnByDate(LocalDate returnBy) {
        return getCurrentDate().plusDays(LOAN_LENGTH);
    }

    @Override
    public void returnLoan(long loanId) {
        Loan loan = loanRepository.retrieveLoanByLoanId(loanId);
        if (loan == null) {
            throw new EntityNotFoundException("Loan not found with id: " + loanId);
        }

        if (loan.getLoanStatus() == LoanStatus.RETURNED) {
            throw new IllegalStateException("Loan has already been returned.");
        }

        loan.setLoanStatus(LoanStatus.RETURNED);
        loanRepository.updateLoan(loan);
        returnToCatalog(loan.getProductCopy());
    }

    @Override
    public void returnToCatalog(long productCopyId) {
        ProductCopy productCopy = productRepository.retrieveProductCopyById(productCopyId)
                .orElseThrow(() -> new EntityNotFoundException("ProductCopy not found with ID: " + productCopyId));

        productCopy.setAvailabilityStatus(ProductCopyStatus.AVAILABLE);
        productRepository.updateProductCopy(productCopy);
    }

    @Override
    public void handleOverdueLoans() {
        LocalDate currentDate = getCurrentDate();

        List<Loan> activeLoans = loanRepository.retrieveAllActiveLoans();
        for (Loan loan : activeLoans) {
            try {
                if (loan.getLoanStatus().isOverdue(currentDate, loan)) {
                    loan.setLoanStatus(LoanStatus.OVERDUE);
                    loanRepository.updateLoan(loan);
                }
            } catch (EntityNotFoundException e) {
                System.out.println("No active loans found" + e.getMessage());
            }
        }
    }

    @Override
    public boolean checkIsLate(Loan loan) {
        LocalDate currentDate = getCurrentDate();
        boolean isLate = loan.getLoanStatus().isOverdue(currentDate, loan);

        if (isLate) {
            updateLoanStatusIfNeeded(loan);
        }

        return isLate;
    }

    @Override
    public void updateLoanStatusIfNeeded(Loan loan) {
        if (loan.getLoanStatus() != LoanStatus.OVERDUE) {
            loan.setLoanStatus(LoanStatus.OVERDUE);
            loanRepository.updateLoan(loan);
        }
    }

    @Override
    public Loan retrieveLoanByLoanId(long loanId) {
        Loan loan = loanRepository.retrieveLoanByLoanId(loanId);
        if (loan == null) {
            throw new EntityNotFoundException("No loan found for Loan ID: " + loanId);
        }
        return loan;
    }

    @Override
    public List<Loan> retrieveActiveLoansByMembershipId(long membershipId) {
        List<Loan> loans = loanRepository.retrieveActiveLoansByMembershipId(membershipId);
        if (loans.isEmpty()) {
            throw new EntityNotFoundException("Membership with ID" + membershipId + " not found.");
        }
        return loans;
    }

    @Override
    public Loan convertReservationToLoan(Reservation reservation) {
        reservationService.markReservationAsLoaned(reservation.getReservationId());

        Loan loan = new Loan();
        loan.setProductCopy(reservation.getProductCopy());
        loan.setMembership(reservation.getMembership());
        loan.setStartDate(getCurrentDate());
        loan.setReturnBy(getCurrentDate().plusDays(LOAN_LENGTH));
        loan.setLoanStatus(LoanStatus.ACTIVE);
        return loanRepository.store(loan);
    }

    @Override
    public void validateLoanRequestDTO(LoanDTO loanDTO) {
        if (loanDTO == null) {
            throw new IllegalArgumentException("LoanRequestDTO cannot be null.");
        }
    }

    private LocalDate getCurrentDate() {
        return LocalDate.now();
    }


    private void setLoanDates(Loan loan) {
        loan.setStartDate(LocalDate.now());
        loan.setReturnBy(getCurrentDate().plusDays(LOAN_LENGTH));
    }


    public void checkDoesLoanExceedLimitForMembership (Membership membership) {
        List<Loan> activeLoanList = retrieveActiveLoansByMembershipId(membership.getMembershipId());
        int numberOfLoans = activeLoanList.size();

        long membershipTypeId = membership.getMembershipType().getMembershipTypeId();
        MembershipType membershipType = membershipTypeRepository.retrieveMembershipTypeById(membershipTypeId).get();
        int maxNumberOfLoans = membershipType.getMaxLendings();
        if (numberOfLoans >= maxNumberOfLoans) {
            throw new IllegalStateException("Loan would exceed limit for MembershipType");
        }
    }

    public void checkDoesLoanExceedLimitForGenre (Membership membership, ProductCopy productCopy) {
        long membershipId = membership.getMembershipId();
        long membershipTypeId = membership.getMembershipType().getMembershipTypeId();
        long genreId = productCopy.getPhysicalProductId().getGenre().getGenreId();

        int maxLendingsForGenre = membershipTypeRepository.retrieveLendingLimitByGenreAndMembershipType(membershipTypeId, genreId);
        int currentLendingsForGenre = loanRepository.retrieveCurrentGenreLoanCount(membershipId, genreId);

        if (currentLendingsForGenre >= maxLendingsForGenre && maxLendingsForGenre > 0) {
            throw new IllegalStateException("Loan would exceed limit for Genre");
        }
    }

    private void setLoanStatus(Loan loan) {
        loan.setLoanStatus(LoanStatus.ACTIVE);
    }

}
