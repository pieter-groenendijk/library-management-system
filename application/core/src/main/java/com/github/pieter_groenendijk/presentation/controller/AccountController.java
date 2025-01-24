package com.github.pieter_groenendijk.presentation.controller;

import com.github.pieter_groenendijk.domain.exception.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import com.github.pieter_groenendijk.domain.services.account.IAccountService;
import com.github.pieter_groenendijk.domain.entities.account.Account;
import com.github.pieter_groenendijk.dto.account.AccountDTO;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/account") 
public class AccountController {
    private final IAccountService SERVICE;

    private AccountController(
        IAccountService service
    ) {
        this.SERVICE = service;
    }

    @Operation(summary = "Retrieve an account", description = "Retrieve an account by Id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Account found"),
        @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @GetMapping("/{id}")
    public Account retrieveAccountById(@PathVariable("id") long id) throws Exception {
        try {
            return SERVICE.retrieveAccountById(id);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("");
        }
    }

    @Operation(summary = "Create an account", description = "Add a new account to the database")
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountDTO account) throws Exception {
        SERVICE.store(account);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Update an account", description = "Update an account in the database")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable("id") long id, @RequestBody AccountDTO account) throws Exception {
        SERVICE.update(id, account);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Set account blocked", description = "Set an account from blocked to unblocked and back")
    @PostMapping("/setBlocked/{id}/{blocked}")
    public ResponseEntity<?> setAccountBlocked(@PathVariable("id") long id, @PathVariable boolean newValue) throws Exception {
        SERVICE.setIsBlocked(id, newValue);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Softdelete an account", description = "Softdelete an account in the database")
    @PutMapping("/softdelete/{id}")
    public ResponseEntity<?> softDeleteAccount(@PathVariable("id") long id) throws Exception {
        SERVICE.softDeleteAccount(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
