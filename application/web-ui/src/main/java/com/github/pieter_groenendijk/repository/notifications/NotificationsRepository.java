package com.github.pieter_groenendijk.repository.notifications;

import com.github.pieter_groenendijk.model.Notification;
import com.github.pieter_groenendijk.repository.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@org.springframework.stereotype.Repository
public class NotificationsRepository extends Repository {
    private final String BASE_PATH = "/notifications";

    public NotificationsRepository(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public Optional<Notification> retrieve(Long notificationId) {
        return super.retrieveSingle(
            BASE_PATH + "/{notificationId}",
            Notification.class,
            notificationId
        );
    }

//    public List<Notification> retrieveRecent(Long accountId) {
//        super.retrieve(
//            BASE_PATH + "/{accountId}/recent",
//            Notification[].class,
//            accountId
//        );
//    }
}
