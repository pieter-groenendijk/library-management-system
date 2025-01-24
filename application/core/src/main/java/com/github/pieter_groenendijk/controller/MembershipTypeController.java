package com.github.pieter_groenendijk.controller;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import com.github.pieter_groenendijk.service.IAccountService;
import com.github.pieter_groenendijk.entity.MembershipType;
import com.github.pieter_groenendijk.entity.LendingLimit;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.github.pieter_groenendijk.dto.MembershipTypeRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;

@RestController
@RequestMapping("/membershipType") 
public class MembershipTypeController {
    private final IAccountService ACCOUNT_SERVICE;

    private MembershipTypeController(
        IAccountService accountService
    )
    {
        this.ACCOUNT_SERVICE = accountService;
    }

    @Operation(summary = "Retrieve a membershipType", description = "Retrieve a membershipType by Id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "MembershipType found"),
        @ApiResponse(responseCode = "404", description = "MembershipType not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveMembershipTypeById(@PathVariable("id") long id) {
        MembershipType membershipType = ACCOUNT_SERVICE.retrieveMembershipTypeById(id);
        return ResponseEntity.ok(membershipType);
    }

    @Operation(summary = "Create a membershipType", description = "Add a new membershipType to the database")
    @PostMapping
    public ResponseEntity<?> createMembershipType(@RequestBody MembershipTypeRequestDTO membershipType) {
        ACCOUNT_SERVICE.store(membershipType);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Update a membershipType", description = "Change a membershipType ")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMembershipType(@PathVariable("id") long id, @RequestBody MembershipTypeRequestDTO membershipType) {
        ACCOUNT_SERVICE.update(id, membershipType);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Retrieve a list of memberships", description = "Retrieve a list of memberships")
    @GetMapping("/getAll")
    public ResponseEntity<List<MembershipType>> retrieveMembershipTypeList() {
        List<MembershipType> membershipTypes = ACCOUNT_SERVICE.retrieveMembershipTypeList();
        if (membershipTypes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(membershipTypes);
        }
    }

    @Operation(summary = "Softdelete a membershipType", description = "Softdelete a membershipType in the database")
    @PutMapping("/softdelete/{id}")
    public ResponseEntity<?> softDeleteMembershipType(@PathVariable("id") long id) {
        ACCOUNT_SERVICE.softDeleteMembershipType(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    //LendingLimit

    @Operation(summary = "Retrieve a LendingLimit", description = "Retrieve a LendingLimit by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "LendingLimit found"),
            @ApiResponse(responseCode = "404", description = "LendingLimit not found")
    })
    @GetMapping("/lendingLimit/{id}")
    public ResponseEntity<?> retrieveLendingLimitById(@PathVariable("id") long id ) {
        LendingLimit lendingLimit = ACCOUNT_SERVICE.retrieveLendingLimitById(id);
        return ResponseEntity.ok(lendingLimit);
    }

    @Operation(summary = "Create a lendingLimit", description = "Add a new lendingLimit to the database")
    @PostMapping("/lendingLimit")
    public ResponseEntity<?> createLendingLimit(@RequestBody LendingLimit lendingLimit) {
        ACCOUNT_SERVICE.store(lendingLimit);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Update a lendingLimit", description = "Change a lendingLimit ")
    @PutMapping("/lendingLimit/{id}")
    public ResponseEntity<?> updateLendingLimit(@PathVariable("id") long id, @RequestBody LendingLimit lendingLimit) {
        ACCOUNT_SERVICE.update(id, lendingLimit);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Retrieve a list of lendingLimits", description = "Retrieve a list of lendinglimits for a membershipType")
    @GetMapping("/lendingLimit/getAll/{id}")
    public ResponseEntity<List<LendingLimit>> retrieveGenreList(@PathVariable("id") long membershipTypeId) {
        List<LendingLimit> lendingLimitList = ACCOUNT_SERVICE.retrieveLendingLimitList(membershipTypeId);
        if (lendingLimitList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(lendingLimitList);
        }
    }

    @Operation(summary = "Softdelete a lendingLimit", description = "Softdelete a lendingLimit in the database")
    @PutMapping("/lendingLimit/softdelete/{id}")
    public ResponseEntity<?> softDeleteLendingLimit(@PathVariable("id") long id) {
        ACCOUNT_SERVICE.softDeleteLendingLimit(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}