package com.github.pieter_groenendijk.repository.notifications;

import com.github.pieter_groenendijk.model.notification.Notification;
import com.github.pieter_groenendijk.repository.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class NotificationsRepository extends Repository {
    private final String BASE_PATH = "/notifications";

    public NotificationsRepository(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public Optional<Notification> retrieve(Long notificationId) {
        return super.getSingle(
            BASE_PATH + "/{notificationId}",
            Notification.class,
            notificationId
        );
    }

    public List<Notification> retrieveRecent(Long accountId) {
        return super.getList(
            BASE_PATH + "/{accountId}/recent",
            Notification[].class,
            accountId
        );
    }
}
