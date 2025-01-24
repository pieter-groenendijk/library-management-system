package com.github.pieter_groenendijk.domain.services.notification.scheduling;

import com.github.pieter_groenendijk.datasource.repositories.scheduling.ITaskRepository;
import com.github.pieter_groenendijk.domain.entities.notification.Notification;
import com.github.pieter_groenendijk.domain.shared.scheduling.LongTermTaskScheduler;
import com.github.pieter_groenendijk.domain.shared.scheduling.TaskScheduler;
import com.github.pieter_groenendijk.domain.services.notification.sendstrategies.registry.NotificationSendStrategyRegistry;

public class NotificationScheduler extends LongTermTaskScheduler<Notification> {
    private final NotificationSendStrategyRegistry SEND_STRATEGY_REGISTRY;

    public NotificationScheduler(
        ITaskRepository<Notification> notificationTaskRepository,
        TaskScheduler scheduler,
        NotificationSendStrategyRegistry sendStrategyRegistry
    ) {
        super(notificationTaskRepository, scheduler);

        this.SEND_STRATEGY_REGISTRY = sendStrategyRegistry;
    }

    public NotificationScheduler(
        ITaskRepository<Notification> notificationTaskRepository,
        int amountOfThreads,
        NotificationSendStrategyRegistry sendStrategyRegistry
    ) {
        super(notificationTaskRepository, amountOfThreads);

        this.SEND_STRATEGY_REGISTRY = sendStrategyRegistry;
    }

    @Override
    protected void executeTask(Notification task) {
        this.SEND_STRATEGY_REGISTRY
            .fromStrategyType(task.getSendStrategyType())
            .send(task);
    }
}
