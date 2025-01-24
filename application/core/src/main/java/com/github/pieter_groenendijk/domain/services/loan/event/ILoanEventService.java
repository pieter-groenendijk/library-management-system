package com.github.pieter_groenendijk.domain.services.loan.event;

import com.github.pieter_groenendijk.domain.entities.loan.Loan;

public interface ILoanEventService {
    void handleEventsForNewLoan(Loan loan) throws Exception;
}
