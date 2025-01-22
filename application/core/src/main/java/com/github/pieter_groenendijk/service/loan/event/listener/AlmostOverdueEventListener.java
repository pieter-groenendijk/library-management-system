package com.github.pieter_groenendijk.service.loan.event.listener;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.service.event.listener.EventListener;
import com.github.pieter_groenendijk.service.notification.NotificationOrchestrator;

class AlmostOverdueEventListener extends EventListener<Loan> {
    private final NotificationOrchestrator NOTIFICATION_SERVICE;

    public AlmostOverdueEventListener(NotificationOrchestrator notificationOrchestrator) {
        super(
            EventType.ALMOST_OVERDUE_LOAN
        );
        this.NOTIFICATION_SERVICE = notificationOrchestrator;
    }

    @Override
    public void tryReact(Loan loan) throws Exception {
        this.NOTIFICATION_SERVICE.scheduleAlmostOverdueLoanNotification(loan);
    }
}
