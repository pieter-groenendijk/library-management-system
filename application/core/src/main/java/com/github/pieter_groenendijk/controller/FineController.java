package com.github.pieter_groenendijk.controller;

import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import com.github.pieter_groenendijk.model.DTO.FineSummaryDTO;
import com.github.pieter_groenendijk.repository.fine.FineRepository;
import com.github.pieter_groenendijk.service.fine.FineService;
import com.github.pieter_groenendijk.service.fine.IFineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account/{accountId}/fines")
public class FineController {
    private final IFineService SERVICE;

    public FineController() {
        this.SERVICE = new FineService(
            new FineRepository(new SessionFactoryFactory().create())
        );
    }

    @GetMapping("/unpaid-summary")
    public FineSummaryDTO retrieveUnpaidFinesSummary(
        @PathVariable("accountId") Long accountId
    ) throws Exception {
        return this.SERVICE.retrieveUnpaidFinesSummary(accountId);
    }
}
