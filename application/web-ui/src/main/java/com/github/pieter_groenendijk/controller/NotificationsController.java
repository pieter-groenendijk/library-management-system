package com.github.pieter_groenendijk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotificationsController {
    @GetMapping("/notifications")
    public String render(Model model) {
        model.addAttribute(
            "title",
            "Notifications"
        );

        return "notification-overview";
    }
}
