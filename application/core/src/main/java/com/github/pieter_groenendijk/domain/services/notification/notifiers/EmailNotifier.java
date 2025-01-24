package com.github.pieter_groenendijk.domain.services.notification.notifiers;

import com.github.pieter_groenendijk.domain.entities.notification.Notification;

public class EmailNotifier extends ChannelNotifier {
    @Override
    protected void attempt(Notification task) throws Exception {
    }
}
