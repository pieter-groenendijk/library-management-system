package com.github.pieter_groenendijk.repository.loan.event;

import com.github.pieter_groenendijk.domain.entities.Loan;

public interface ILoanEventRepostory {
    void cancelDuenessEventsForLoan(Loan loan) throws Exception;
}
