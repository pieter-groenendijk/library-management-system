package com.github.pieter_groenendijk.controller;

import com.github.pieter_groenendijk.exception.InputValidationException;
import com.github.pieter_groenendijk.model.DTO.NotificationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    public NotificationController() {

    }

    @Operation(summary = "Retrieve the notifications the account should have received")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200"
        ),
        @ApiResponse(
            responseCode = "404"
        )
    })
    @GetMapping("/{accountId}/recent")
    public ResponseEntity<List<NotificationDTO>> retrieveRecentReceivedNotifications(
        @PathVariable Long accountId,
        @RequestParam(
            value = "maxAmount",
            defaultValue = "10"
        ) int maxAmount
    ) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(List.of());
    }
}
