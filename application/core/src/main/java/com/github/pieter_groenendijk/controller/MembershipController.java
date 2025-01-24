package com.github.pieter_groenendijk.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import com.github.pieter_groenendijk.service.IAccountService;
import com.github.pieter_groenendijk.dto.MembershipRequestDTO;
import com.github.pieter_groenendijk.entity.Membership;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/membership")
public class MembershipController{
	private final IAccountService ACCOUNT_SERVICE;

	public MembershipController(
        IAccountService accountService
    )
	{
        this.ACCOUNT_SERVICE = accountService;
	}

	@Operation(summary = "Retrieve a membership", description = "Retrieve a membership by Id")
	@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Membership found"),
        @ApiResponse(responseCode = "404", description = "Membership not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveMembershipById(@PathVariable("id") long id) {
        Membership membership = ACCOUNT_SERVICE.retrieveMembershipById(id);
        return ResponseEntity.ok(membership);
    }

    @Operation(summary = "Retrieve membershipList", description = "Retrieve memberships by Account Id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Memberships found"),
        @ApiResponse(responseCode = "404", description = "No memberships found for the given Account Id")
    })
    @GetMapping("/account/{accountId}")
    public ResponseEntity<?> retrieveMembershipsByAccountId(@PathVariable("accountId") long accountId) {
        List<Membership> memberships = ACCOUNT_SERVICE.retrieveMembershipsByAccountId(accountId);
        if (memberships.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(memberships);
        }
    }

    @Operation(summary = "Create a membership", description = "Add a new membership to the database")
    @PostMapping
    public ResponseEntity<?> createMembership(@RequestBody MembershipRequestDTO request) throws Exception {
        ACCOUNT_SERVICE.store(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Update a membership", description = "Update a membership in the database")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMembership(@PathVariable("id") long id, @RequestBody MembershipRequestDTO request){
        ACCOUNT_SERVICE.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Softdelete a membership", description = "Softdelete an membership in the database")
    @PutMapping("/softdelete/{id}")
    public ResponseEntity<?> softDeleteMembership(@PathVariable("id") long id) {
        ACCOUNT_SERVICE.softDeleteMembership(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
