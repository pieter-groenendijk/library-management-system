package com.github.pieter_groenendijk.domain.services.notification.orchestration;

import com.github.pieter_groenendijk.domain.entities.account.Account;
import com.github.pieter_groenendijk.domain.entities.loan.Loan;
import com.github.pieter_groenendijk.domain.entities.notification.LoanNotification;
import com.github.pieter_groenendijk.repositories.notification.INotificationRepository;
import com.github.pieter_groenendijk.domain.shared.scheduling.DetachedTask;
import com.github.pieter_groenendijk.domain.services.notification.generators.AlmostOverdueLoanNotificationGenerator;
import com.github.pieter_groenendijk.domain.services.notification.generators.OverdueLoanNotificationGenerator;

public class DetachedNotificationFactory {
    private final OverdueLoanNotificationGenerator OVERDUE_GENERATOR;
    private final AlmostOverdueLoanNotificationGenerator ALMOST_OVERDUE_GENERATOR;

    public DetachedNotificationFactory(
        INotificationRepository repository
    ) {
        this.OVERDUE_GENERATOR = new OverdueLoanNotificationGenerator(repository);
        this.ALMOST_OVERDUE_GENERATOR = new AlmostOverdueLoanNotificationGenerator(repository);
    }

    public DetachedTask<LoanNotification> createOverdueLoanNotification(Account account, Loan loan) {
        return OVERDUE_GENERATOR.generate(
            account,
            loan
        );
    }

    public DetachedTask<LoanNotification> createAlmostOverdueLoanNotification(Account account, Loan loan) {
        return this.ALMOST_OVERDUE_GENERATOR.generate(
            account,
            loan
        );
    }
}

