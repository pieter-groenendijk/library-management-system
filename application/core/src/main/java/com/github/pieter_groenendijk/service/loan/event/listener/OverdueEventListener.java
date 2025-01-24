package com.github.pieter_groenendijk.service.loan.event.listener;

import com.github.pieter_groenendijk.domain.entities.loan.Loan;
import com.github.pieter_groenendijk.domain.entities.event.EventType;
import com.github.pieter_groenendijk.service.event.listener.EventListener;
import com.github.pieter_groenendijk.service.loan.event.scheduling.LoanEventScheduler;
import com.github.pieter_groenendijk.service.notification.orchestration.NotificationOrchestrator;

class OverdueEventListener extends EventListener<Loan> {
    private final LoanEventScheduler LOAN_EVENT_SCHEDULER;
    private final NotificationOrchestrator NOTIFICATION_SERVICE;

    public OverdueEventListener(
        LoanEventScheduler scheduler,
        NotificationOrchestrator notificationOrchestrator
    ) {
        super(
            EventType.OVERDUE_LOAN
        );

        this.LOAN_EVENT_SCHEDULER = scheduler;
        this.NOTIFICATION_SERVICE = notificationOrchestrator;
    }

    @Override
    public void tryReact(Loan loan) throws Exception {
        this.LOAN_EVENT_SCHEDULER.scheduleDayOverdueLoanEvent(loan);

        this.NOTIFICATION_SERVICE.scheduleOverdueLoanNotification(loan);
    }
}