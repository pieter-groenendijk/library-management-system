package com.github.pieter_groenendijk.controller;

import com.github.pieter_groenendijk.repository.notifications.NotificationsRepository;
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
        @PathVariable("notificationId") String notificationId,
        Model model
    ) {
        model.addAttribute(
            "title",
            "Notification"
        );

        model.addAttribute(
            "notification",
            this.REPOSITORY.retrieve(1L)
        );

        return "notification/index";
    }
}
