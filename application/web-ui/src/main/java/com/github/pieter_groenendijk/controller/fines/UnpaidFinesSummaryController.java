package com.github.pieter_groenendijk.controller.fines;

import com.github.pieter_groenendijk.repository.fines.FineRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UnpaidFinesSummaryController {
    private final FineRepository REPOSITORY;

    public UnpaidFinesSummaryController(
        FineRepository repository
    ) {
        this.REPOSITORY = repository;
    }

    @GetMapping("/unpaid-fines")
    public String render(Model model) {
        model.addAttribute(
            "title",
            "Unpaid Fines Summary"
        );

        this.REPOSITORY.retrieveUnpaidFinesSummary(1L).ifPresent((fineSummary -> {
            model.addAttribute(
                "summary",
                fineSummary
            );
        }));

        return "unpaid-fines/unpaid-fines";
    }

    @PostMapping("/unpaid-fines/pay")
    public String payFines() {
        return "redirect:/unpaid-fines";
    }
}
