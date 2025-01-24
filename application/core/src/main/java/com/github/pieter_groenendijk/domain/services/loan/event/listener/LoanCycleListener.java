package com.github.pieter_groenendijk.domain.services.loan.event.listener;

import com.github.pieter_groenendijk.domain.services.event.emitting.EventEmitterPool;
import com.github.pieter_groenendijk.domain.services.event.listener.EventListener;
import com.github.pieter_groenendijk.domain.services.loan.event.scheduling.LoanEventScheduler;
import com.github.pieter_groenendijk.domain.services.loan.fine.LoanFineService;
import com.github.pieter_groenendijk.domain.services.notification.orchestration.NotificationOrchestrator;
import com.github.pieter_groenendijk.domain.services.product.EventPoolListener;

@Deprecated(
    forRemoval = true
)
public class LoanCycleListener extends EventPoolListener {
    public LoanCycleListener(
        EventEmitterPool eventEmitterPool,
        LoanEventScheduler scheduler,
        LoanFineService fineService,
        NotificationOrchestrator notificationOrchestrator
    ) {
        super(
            eventEmitterPool,
            new EventListener[]{
                new AlmostOverdueEventListener(
                    notificationOrchestrator
                ),
                new OverdueEventListener(
                    scheduler,
                    notificationOrchestrator
                ),
                new DayOverdueEventListener(
                    scheduler,
                    fineService
                )
            }
        );
    }
}
