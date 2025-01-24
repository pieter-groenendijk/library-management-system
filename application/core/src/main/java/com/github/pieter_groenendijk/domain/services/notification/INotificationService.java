package com.github.pieter_groenendijk.domain.services.notification;

import com.github.pieter_groenendijk.shared.dto.notification.NotificationDTO;

import java.util.List;

public interface INotificationService {
    List<NotificationDTO> retrieveRecentReceivedNotifications(Long accountId, int maxAmount) throws Exception;
    NotificationDTO retrieve(Long notificationId) throws Exception;
}
