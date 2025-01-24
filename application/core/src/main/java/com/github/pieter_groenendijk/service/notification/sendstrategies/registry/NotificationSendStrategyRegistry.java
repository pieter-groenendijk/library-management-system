package com.github.pieter_groenendijk.service.notification.sendstrategies.registry;


import com.github.pieter_groenendijk.service.notification.sendstrategies.*;

public class NotificationSendStrategyRegistry {
    private final AlertNotificationSendStrategy ALERT_STRATEGY;
    private final WarningNotificationSendStrategy WARNING_STRATEGY;
    private final ReminderNotificationSendStrategy REMINDER_STRATEGY;

    public NotificationSendStrategyRegistry(
        NotificationSendStrategyAssembler assembler
    ) {
        this.ALERT_STRATEGY = assembler.createAlertStrategy();
        this.WARNING_STRATEGY = assembler.createWarningStrategy();
        this.REMINDER_STRATEGY = assembler.createReminderStrategy();
    }

    public NotificationSendStrategy fromStrategyType(SendStrategyType type) {
        return switch (type) {
            case ALERT -> ALERT_STRATEGY;
            case WARNING -> WARNING_STRATEGY;
            case REMINDER -> REMINDER_STRATEGY;
        };
    }
}
