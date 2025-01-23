package com.github.pieter_groenendijk.service.notification.orchestration;

import com.github.pieter_groenendijk.entity.Loan;
import com.github.pieter_groenendijk.repository.IAccountRepository;
import com.github.pieter_groenendijk.repository.notification.INotificationRepository;
import com.github.pieter_groenendijk.scheduling.TaskScheduler;
import com.github.pieter_groenendijk.service.notification.scheduling.NotificationScheduler;
import com.github.pieter_groenendijk.service.notification.sendstrategies.NotificationSendStrategyAssembler;
import com.github.pieter_groenendijk.service.notification.sendstrategies.registry.NotificationSendStrategyRegistry;

public class NotificationOrchestrator {
    private final DetachedNotificationFactory FACTORY;
    private final NotificationScheduler SCHEDULER;

    private final IAccountRepository ACCOUNT_REPOSITORY;

    public NotificationOrchestrator(
        TaskScheduler scheduler,
        INotificationRepository repository,
        IAccountRepository accountRepository
    ) {
        this.FACTORY = new DetachedNotificationFactory(
            repository
        );

        this.SCHEDULER = new NotificationScheduler(
            repository,
            scheduler,
            new NotificationSendStrategyRegistry(
                new NotificationSendStrategyAssembler()
            )
        );

        this.ACCOUNT_REPOSITORY = accountRepository;
    }

    public void scheduleOverdueLoanNotification(Loan loan) throws Exception {
        this.SCHEDULER.schedule(
            this.FACTORY.createOverdueLoanNotification(
                this.ACCOUNT_REPOSITORY.retrieveAccountFromLoan(loan).orElseThrow(),
                loan
            )
        );
    }

    public void scheduleAlmostOverdueLoanNotification(Loan loan) throws Exception {
        this.SCHEDULER.schedule(
            this.FACTORY.createAlmostOverdueLoanNotification(
                this.ACCOUNT_REPOSITORY.retrieveAccountFromLoan(loan).orElseThrow(),
                loan
            )
        );
    }
}
