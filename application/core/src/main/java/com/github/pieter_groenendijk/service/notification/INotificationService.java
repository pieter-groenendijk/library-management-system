package com.github.pieter_groenendijk.service.notification;

import com.github.pieter_groenendijk.dto.NotificationDTO;

import java.util.List;

public interface INotificationService {
    List<NotificationDTO> retrieveRecentReceivedNotifications(Long accountId, int maxAmount) throws Exception;
    NotificationDTO retrieve(Long notificationId) throws Exception;
}
