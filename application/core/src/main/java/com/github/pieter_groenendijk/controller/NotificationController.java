package com.github.pieter_groenendijk.controller;

import com.github.pieter_groenendijk.exception.InputValidationException;
import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import com.github.pieter_groenendijk.model.DTO.NotificationDTO;
import com.github.pieter_groenendijk.repository.notification.NotificationRepository;
import com.github.pieter_groenendijk.service.notification.INotificationService;
import com.github.pieter_groenendijk.service.notification.NotificationService;
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

    public NotificationController() {
        this.SERVICE = new NotificationService(
            new NotificationRepository(new SessionFactoryFactory().create()) // TODO: Dependency injection instead, not easily tested right now
        );
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
}
