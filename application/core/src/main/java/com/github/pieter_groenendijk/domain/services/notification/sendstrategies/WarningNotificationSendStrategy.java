package com.github.pieter_groenendijk.domain.services.notification.sendstrategies;

import com.github.pieter_groenendijk.domain.services.notification.notifiers.AppNotifier;
import com.github.pieter_groenendijk.domain.services.notification.notifiers.EmailNotifier;
import com.github.pieter_groenendijk.domain.services.notification.notifiers.Notifier;

public class WarningNotificationSendStrategy extends NotificationSendStrategy {
    public WarningNotificationSendStrategy(EmailNotifier emailNotifier, AppNotifier appNotifier) {
        super(new Notifier[]{
            emailNotifier,
            appNotifier
        });
    }
}
