package com.github.pieter_groenendijk.repository.notification;

import com.github.pieter_groenendijk.entity.notification.Notification;
import com.github.pieter_groenendijk.repository.scheduling.ITaskRepository;

import java.util.List;
import java.util.Optional;

public interface INotificationRepository extends ITaskRepository<Notification> {
    List<Notification> retrieveRecentReceivedNotifications(Long accountId, int maxAmount) throws Exception;
    Optional<Notification> retrieve(Long notificationId) throws Exception;
}
