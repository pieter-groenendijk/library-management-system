package com.github.pieter_groenendijk.controller.notifications;

import com.github.pieter_groenendijk.repository.notifications.NotificationsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotificationsController {
    private final NotificationsRepository REPOSITORY;

    public NotificationsController(
        NotificationsRepository repository
    ) {
        this.REPOSITORY = repository;
    }

    @GetMapping("/notifications")
    public String render(Model model) {
        model.addAttribute(
            "title",
            "Notifications"
        );

        model.addAttribute(
            "notifications",
            this.REPOSITORY.retrieveRecent(1L)
        );

        return "notifications/index";
    }
}
