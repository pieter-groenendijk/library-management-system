package com.github.pieter_groenendijk.controller;

import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import com.github.pieter_groenendijk.model.DTO.FineSummaryDTO;
import com.github.pieter_groenendijk.repository.fine.FineRepository;
import com.github.pieter_groenendijk.service.fine.FineService;
import com.github.pieter_groenendijk.service.fine.IFineService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public FineSummaryDTO retrieveUnpaidSummary(
        @PathVariable("accountId") Long accountId
    ) throws Exception {
        return this.SERVICE.retrieveUnpaidSummary(accountId);
    }

    @Operation(summary = "Pay debts", description = "Pay fine debts")
    @PostMapping("/pay")
    public ResponseEntity<?> payUnpaid(
        @PathVariable("accountId") Long accountId
    ) throws Exception {
        this.SERVICE.payUnpaid(accountId);

        return ResponseEntity
            .status(HttpStatus.OK)
            .build();
    }
}
