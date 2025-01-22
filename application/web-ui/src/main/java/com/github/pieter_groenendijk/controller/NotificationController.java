package com.github.pieter_groenendijk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NotificationController {
    @GetMapping("/notifications/{notificationId}")
    public String render(
        @PathVariable("notificationId") String notificationId,
        Model model
    ) {
        model.addAttribute(
            "title",
            "Notification"
        );

        return "notification-single";
    }
}
