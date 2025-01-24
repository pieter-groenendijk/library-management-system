package com.github.pieter_groenendijk.domain.services.notification.sendstrategies;

import com.github.pieter_groenendijk.domain.services.notification.notifiers.AppNotifier;
import com.github.pieter_groenendijk.domain.services.notification.notifiers.EmailNotifier;
import com.github.pieter_groenendijk.domain.services.notification.notifiers.Notifier;
import com.github.pieter_groenendijk.domain.services.notification.notifiers.SMSNotifier;

public class AlertNotificationSendStrategy extends NotificationSendStrategy {
    public AlertNotificationSendStrategy(EmailNotifier emailNotifier, SMSNotifier smsNotifier, AppNotifier appNotifier) {
        super(new Notifier[]{
            emailNotifier,
            smsNotifier,
            appNotifier
        });
    }
}
