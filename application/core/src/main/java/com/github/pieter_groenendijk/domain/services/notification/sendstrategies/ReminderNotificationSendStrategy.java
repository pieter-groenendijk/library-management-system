package com.github.pieter_groenendijk.domain.services.notification.sendstrategies;


import com.github.pieter_groenendijk.domain.services.notification.notifiers.EmailNotifier;
import com.github.pieter_groenendijk.domain.services.notification.notifiers.Notifier;

public class ReminderNotificationSendStrategy extends NotificationSendStrategy {
    public ReminderNotificationSendStrategy(EmailNotifier emailNotifier) {
        super(new Notifier[]{
            emailNotifier
        });
    }
}
