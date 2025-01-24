package com.github.pieter_groenendijk.domain.services.loan.fine;

import com.github.pieter_groenendijk.domain.entities.loan.Loan;
import com.github.pieter_groenendijk.repository.IAccountRepository;
import com.github.pieter_groenendijk.repository.fine.IFineRepository;
import com.github.pieter_groenendijk.domain.services.fine.processor.FineProcessor;
import com.github.pieter_groenendijk.domain.services.fine.type.FineTypeRegistry;

public class LoanFineService {
    private final LoanFineFactory FACTORY;
    private final FineProcessor PROCESSOR;

    private final IAccountRepository ACCOUNT_REPOSITORY;

    public LoanFineService(
        IAccountRepository accountRepository,
        IFineRepository fineRepository
    ) {
        this.ACCOUNT_REPOSITORY = accountRepository;

        // TODO: Pass through dependency injection, maybe?
        this.FACTORY = new LoanFineFactory(
            new FineTypeRegistry()
        );

        // TODO: Pass through dependency injection, maybe?
        this.PROCESSOR = new FineProcessor(
            accountRepository,
            fineRepository,
            5000 // TODO: Make dynamic for later requirements
        );
    }

    public void declareDayOverdueFine(Loan loan) {
        try {
            this.PROCESSOR.addFine(
                this.FACTORY.createDayOverdueFine(
                    this.ACCOUNT_REPOSITORY.retrieveAccountFromLoan(loan).orElseThrow(),
                    loan
                )
            );
        } catch (Exception exception) {
            // TODO: Logging
        }
    }

}
