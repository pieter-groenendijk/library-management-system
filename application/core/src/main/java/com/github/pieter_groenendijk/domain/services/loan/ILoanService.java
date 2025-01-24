package com.github.pieter_groenendijk.domain.services.loan;

import com.github.pieter_groenendijk.shared.dto.loan.LoanDTO;
import com.github.pieter_groenendijk.domain.entities.loan.Loan;
import com.github.pieter_groenendijk.domain.entities.reservation.Reservation;

import java.time.LocalDate;
import java.util.List;


public interface ILoanService {
    Loan store(LoanDTO loan) throws Exception;

    void extendLoan(long loanId, LocalDate returnBy);

    public LocalDate generateReturnByDate(LocalDate returnBy);

    void returnToCatalog(long productCopyId);
    void returnLoan(long loanId);

    void handleOverdueLoans();

    boolean checkIsLate(Loan loan);

    void updateLoanStatusIfNeeded(Loan loan);

    Loan retrieveLoanByLoanId(long loanId);

    List<Loan> retrieveActiveLoansByMembershipId(long membershipId);

    Loan convertReservationToLoan(Reservation reservation);

    void validateLoanRequestDTO(LoanDTO loanDTO);
}
