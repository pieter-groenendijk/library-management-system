package com.github.pieter_groenendijk.service.notification;

import com.github.pieter_groenendijk.dto.NotificationDTO;
import com.github.pieter_groenendijk.exception.EntityNotFoundException;
import com.github.pieter_groenendijk.repository.notification.INotificationRepository;
import com.github.pieter_groenendijk.service.notification.mapping.NotificationMapper;

import java.util.List;

public class NotificationService implements INotificationService {
    private final INotificationRepository REPOSITORY;
    private final NotificationMapper MAPPER;

    private final int ALLOWED_MAX_AMOUNT_THRESHOLD = 50;

    public NotificationService(
        INotificationRepository repository
    ) {
        this.REPOSITORY = repository;
        this.MAPPER = new NotificationMapper();
    }

    @Override
    public List<NotificationDTO> retrieveRecentReceivedNotifications(Long accountId, int maxAmount) throws Exception {
        maxAmount = this.getEnforcedAllowedMaxAmount(maxAmount);

        return this.MAPPER.toDTO(
            this.REPOSITORY.retrieveRecentReceivedNotifications(
                accountId,
                maxAmount
            )
        );
    }

    @Override
    public NotificationDTO retrieve(Long notificationId) throws Exception {
        return this.MAPPER.toDTO(
            this.REPOSITORY.retrieve(notificationId).orElseThrow(() -> new EntityNotFoundException("Notification not found"))
        );
    }

    private int getEnforcedAllowedMaxAmount(int givenMaxAmount) {
        return Math.min(givenMaxAmount, this.ALLOWED_MAX_AMOUNT_THRESHOLD);
    }
}
