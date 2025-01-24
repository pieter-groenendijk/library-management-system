package com.github.pieter_groenendijk.entity.event;

import com.github.pieter_groenendijk.entity.Loan;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("loan")
public class LoanEvent extends Event<Loan> {
    @ManyToOne
    @JoinColumn(
        name = "loan",
        nullable = false
    )
    private Loan loan;

    public LoanEvent() {}

    @Override
    public Loan getAssociation() {
        return this.getLoan();
    }

    @Override
    public void setAssociation(Loan loan) {
        this.setLoan(loan);
    }

    public Loan getLoan() {
        return this.loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }
}
