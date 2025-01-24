package com.github.pieter_groenendijk.domain.services.loan.fine.generator;

import com.github.pieter_groenendijk.domain.entities.loan.Loan;
import com.github.pieter_groenendijk.domain.entities.fine.FineType;
import com.github.pieter_groenendijk.domain.entities.fine.LoanFine;
import com.github.pieter_groenendijk.domain.services.fine.generator.FineGenerator;

public class LoanFineGenerator extends FineGenerator<Loan, LoanFine> {
    protected LoanFineGenerator(FineType type) {
        super(type);
    }

    @Override
    protected LoanFine generateEmpty() {
        return new LoanFine();
    }

    @Override
    protected void setFineAssociation(LoanFine fine, Loan loan) {
        fine.setLoan(loan);
    }
}
