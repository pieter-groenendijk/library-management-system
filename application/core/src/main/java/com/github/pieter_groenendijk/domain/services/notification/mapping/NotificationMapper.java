package com.github.pieter_groenendijk.domain.services.notification.mapping;

import com.github.pieter_groenendijk.dto.NotificationDTO;
import com.github.pieter_groenendijk.domain.entities.notification.Notification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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
