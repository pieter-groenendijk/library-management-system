package com.github.pieter_groenendijk.controller.fines;

import com.github.pieter_groenendijk.repository.fines.FineRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FinesController {
    private final FineRepository REPOSITORY;

    public FinesController(
        FineRepository repository
    ) {
        this.REPOSITORY = repository;
    }

    @GetMapping("/fines")
    public String render(Model model) {
        model.addAttribute(
            "title",
            "Notifications"
        );

//        model.addAttribute(
//            "notifications",
//            this.REPOSITORY.retrieveRecent(1L)
//        );

        return "notifications/index";
    }
}
