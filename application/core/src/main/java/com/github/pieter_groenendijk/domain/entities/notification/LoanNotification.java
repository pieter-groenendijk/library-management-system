package com.github.pieter_groenendijk.domain.entities.notification;

import com.github.pieter_groenendijk.domain.entities.loan.Loan;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue(value = "loan")
public class LoanNotification extends Notification {
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    @JoinColumn(
        name = "loan",
        nullable = false
    )
    private Loan loan;

    public LoanNotification() {}

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }
}
