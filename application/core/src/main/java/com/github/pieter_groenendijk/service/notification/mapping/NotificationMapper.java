package com.github.pieter_groenendijk.service.notification.mapping;

import com.github.pieter_groenendijk.model.DTO.NotificationDTO;
import com.github.pieter_groenendijk.model.notification.Notification;

import java.util.List;

public class NotificationMapper {
    public NotificationDTO toDTO(Notification notification) {
        NotificationDTO dto = new NotificationDTO();

        dto.setNotificationId(notification.getNotificationId());
        dto.setTitle(notification.getTitle());
        dto.setMessage(notification.getMessage());
        dto.setScheduledAt(notification.getScheduledAt());

        return dto;
    }

    public List<NotificationDTO> toDTO(List<Notification> notifications) {
        return notifications
            .stream()
            .map(this::toDTO)
            .toList();
    }
}
