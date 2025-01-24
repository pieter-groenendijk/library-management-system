package com.github.pieter_groenendijk.domain.services.notification.generators;

import com.github.pieter_groenendijk.domain.entities.loan.Loan;
import com.github.pieter_groenendijk.domain.entities.notification.LoanNotification;
import com.github.pieter_groenendijk.repository.notification.INotificationRepository;
import com.github.pieter_groenendijk.scheduling.TaskStorage;
import com.github.pieter_groenendijk.domain.services.notification.sendstrategies.registry.SendStrategyType;

import java.time.LocalDateTime;

// TODO: Make superclass that abstract away stuff for a generator for a LoanNotification; there is probably common behaviour
public class OverdueLoanNotificationGenerator extends DetachedNotificationGenerator<Loan, LoanNotification> {
    public OverdueLoanNotificationGenerator(INotificationRepository repository) {
        super(
            SendStrategyType.ALERT,
            repository
        );
    }

    @Override
    protected String generateTitle(Loan loan) {
        return "A product is expected to be already returned!";
    }

    @Override
    protected String generateMessage(Loan loan) {
        return "Return the product to prevent further penalties.";
    }

    @Override
    protected LocalDateTime determineScheduleDateTime(Loan loan) {
        return loan
            .getReturnBy()
            .atTime(8, 0);
    }

    @Override
    protected TaskStorage<LoanNotification> generateStorage(Loan loan) {
        return (LoanNotification task) -> {
            // TODO
        };
    }

    @Override
    protected LoanNotification generateEmpty() {
        return new LoanNotification();
    }
}
