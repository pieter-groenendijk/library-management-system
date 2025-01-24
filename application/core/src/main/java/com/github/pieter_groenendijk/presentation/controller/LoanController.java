package com.github.pieter_groenendijk.presentation.controller;

import com.github.pieter_groenendijk.exception.EntityNotFoundException;
import com.github.pieter_groenendijk.dto.LoanRequestDTO;
import com.github.pieter_groenendijk.domain.entities.loan.Loan;
import com.github.pieter_groenendijk.service.loan.ILoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {
    private final ILoanService SERVICE;

    public LoanController(
        ILoanService service
    ) {
        this.SERVICE = service;
    }

    @Operation(summary = "Create a Loan", description = "Create a new Loan")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Loan created"),
            @ApiResponse(responseCode = "404", description = "Membership or Product not found")
    })
    @PostMapping("/store")
    public ResponseEntity<?> store(@RequestBody LoanRequestDTO loanRequestDTO) throws Exception {
        try {
            Loan loan = SERVICE.store(loanRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Retrieve a loan", description = "Retrieve a loan by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan found"),
            @ApiResponse(responseCode = "404", description = "No loan found for the given loanId\"")
    })
    @GetMapping("loan/{loanId}")
    public ResponseEntity<?> retrieveLoanByLoanId(@PathVariable("loanId") long loanId) {
       try {
            Loan loan = SERVICE.retrieveLoanByLoanId(loanId);
            return ResponseEntity.ok(loan);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Retrieve all loans for a membership", description = "Retrieve loans by membershipId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan(s) for MembershipId found"),
            @ApiResponse(responseCode = "204", description = "No loans found for the given membershipId\"")
    })
    @GetMapping("/member/{membershipId}")
    public ResponseEntity<List<Loan>> retrieveActiveLoansByMembershipId(@Parameter(description = "ID of the membership to retrieve loans for", required = true)
                                                                        @PathVariable("membershipId") long membershipId) {
        List<Loan> loans = SERVICE.retrieveActiveLoansByMembershipId(membershipId);
        if (!loans.isEmpty()) {
            return new ResponseEntity<>(loans, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }
    }
}





