package com.github.pieter_groenendijk.presentation.controller;

import com.github.pieter_groenendijk.dto.NotificationDTO;
import com.github.pieter_groenendijk.service.notification.INotificationService;
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
    private final INotificationService SERVICE;

    public NotificationController(
        INotificationService service
    ) {
        this.SERVICE = service;
    }

    @Operation(summary = "Retrieve the notifications the account should have received")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200"
        ),
        @ApiResponse(
            responseCode = "404"
        )
    })
    @GetMapping("/{accountId}/recent")
    public ResponseEntity<List<NotificationDTO>> retrieveRecentReceivedNotifications(
        @PathVariable("accountId") Long accountId,
        @RequestParam(
            value = "maxAmount",
            defaultValue = "10"
        ) int maxAmount
    ) throws Exception {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                this.SERVICE.retrieveRecentReceivedNotifications(accountId, maxAmount)
            );
    }

    @Operation(summary = "Retrieve a notification")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404")
    })
    @GetMapping("/{notificationId}")
    public ResponseEntity<NotificationDTO> retrieve(
        @PathVariable("notificationId") Long notificationId
    ) throws Exception {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                this.SERVICE.retrieve(notificationId)
            );
    }
}
