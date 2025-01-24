package com.github.pieter_groenendijk.domain.services.notification.sendstrategies;

import com.github.pieter_groenendijk.domain.services.notification.notifiers.AppNotifier;
import com.github.pieter_groenendijk.domain.services.notification.notifiers.EmailNotifier;
import com.github.pieter_groenendijk.domain.services.notification.notifiers.SMSNotifier;

public class NotificationSendStrategyAssembler {
    private final EmailNotifier EMAIL_NOTIFIER;
    private final SMSNotifier SMS_NOTIFIER;
    private final AppNotifier APP_NOTIFIER;

    public NotificationSendStrategyAssembler() {
        this(
            new EmailNotifier(),
            new SMSNotifier(),
            new AppNotifier()
        );
    }

    public NotificationSendStrategyAssembler(EmailNotifier emailNotifier, SMSNotifier smsNotifier, AppNotifier appNotifier) {
        this.EMAIL_NOTIFIER = emailNotifier;
        this.SMS_NOTIFIER = smsNotifier;
        this.APP_NOTIFIER = appNotifier;
    }

    public AlertNotificationSendStrategy createAlertStrategy() {
        return new AlertNotificationSendStrategy(
            EMAIL_NOTIFIER,
            SMS_NOTIFIER,
            APP_NOTIFIER
        );
    }

    public WarningNotificationSendStrategy createWarningStrategy() {
        return new WarningNotificationSendStrategy(
            EMAIL_NOTIFIER,
            APP_NOTIFIER
        );
    }

    public ReminderNotificationSendStrategy createReminderStrategy() {
        return new ReminderNotificationSendStrategy(
            EMAIL_NOTIFIER
        );
    }
}
