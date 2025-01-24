package com.github.pieter_groenendijk.datasource.repositories.notification;

import com.github.pieter_groenendijk.domain.entities.notification.Notification;
import com.github.pieter_groenendijk.datasource.repositories.scheduling.ITaskRepository;

import java.util.List;
import java.util.Optional;

public interface INotificationRepository extends ITaskRepository<Notification> {
    List<Notification> retrieveRecentReceivedNotifications(Long accountId, int maxAmount) throws Exception;
    Optional<Notification> retrieve(Long notificationId) throws Exception;
}
