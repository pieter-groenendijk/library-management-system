package com.github.pieter_groenendijk.domain.services.notification.generators;

import com.github.pieter_groenendijk.domain.entities.loan.Loan;
import com.github.pieter_groenendijk.domain.entities.notification.LoanNotification;
import com.github.pieter_groenendijk.repository.notification.INotificationRepository;
import com.github.pieter_groenendijk.scheduling.TaskStorage;
import com.github.pieter_groenendijk.domain.services.notification.sendstrategies.registry.SendStrategyType;

import java.time.LocalDateTime;

public class AlmostOverdueLoanNotificationGenerator extends DetachedNotificationGenerator<Loan, LoanNotification> {
    public AlmostOverdueLoanNotificationGenerator(INotificationRepository repository) {
        super(
            SendStrategyType.WARNING,
            repository
        );
    }

    @Override
    protected String generateTitle(Loan loan) {
        return "A product is expected to be returned soon!";
    }

    @Override
    protected String generateMessage(Loan loan) {
        return "Return the product to prevent any penalties.";
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
