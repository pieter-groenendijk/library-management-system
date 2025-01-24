package com.github.pieter_groenendijk.datasource.repositories.loan.event;

import com.github.pieter_groenendijk.domain.entities.loan.Loan;

public interface ILoanEventRepostory {
    void cancelDuenessEventsForLoan(Loan loan) throws Exception;
}
