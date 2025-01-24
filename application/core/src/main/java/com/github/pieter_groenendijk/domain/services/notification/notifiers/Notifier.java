package com.github.pieter_groenendijk.domain.services.notification.notifiers;

import com.github.pieter_groenendijk.domain.entities.notification.Notification;

public interface Notifier {
    void send(Notification task);
}
