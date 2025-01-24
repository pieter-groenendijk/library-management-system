package com.github.pieter_groenendijk.controller.notifications;

import com.github.pieter_groenendijk.repositories.notifications.NotificationsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NotificationController {
    private final NotificationsRepository REPOSITORY;

    public NotificationController(
        NotificationsRepository repository
    ) {
        this.REPOSITORY = repository;
    }

    @GetMapping("/notifications/{notificationId}")
    public String render(
        @PathVariable("notificationId") Long notificationId,
        Model model
    ) {
        model.addAttribute(
            "title",
            "Notification"
        );

        this.REPOSITORY.retrieve(notificationId).ifPresent(notification -> {
            model.addAttribute(
                "notification",
                notification
            );
        });

        return "notification/index";
    }
}
