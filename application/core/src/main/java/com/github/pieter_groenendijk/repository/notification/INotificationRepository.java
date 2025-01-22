package com.github.pieter_groenendijk.repository.notification;

import com.github.pieter_groenendijk.model.notification.Notification;
import com.github.pieter_groenendijk.repository.scheduling.ITaskRepository;

import java.util.List;

public interface INotificationRepository extends ITaskRepository<Notification> {
    List<Notification> retrieveRecentReceivedNotifications(Long accountId, int maxAmount) throws Exception;
}
