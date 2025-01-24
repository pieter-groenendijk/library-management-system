package com.github.pieter_groenendijk.service.notification;

import com.github.pieter_groenendijk.dto.NotificationDTO;
import com.github.pieter_groenendijk.exception.EntityNotFoundException;
import com.github.pieter_groenendijk.repository.notification.INotificationRepository;
import com.github.pieter_groenendijk.service.notification.mapping.NotificationMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService implements INotificationService {
    private final INotificationRepository REPOSITORY;
    private final NotificationMapper MAPPER;

    private final int ALLOWED_MAX_AMOUNT_THRESHOLD = 50; // TODO: Could centralize this for certain retrievals.

    public NotificationService(
        INotificationRepository repository,
        NotificationMapper mapper
    ) {
        this.REPOSITORY = repository;
        this.MAPPER = mapper;
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
