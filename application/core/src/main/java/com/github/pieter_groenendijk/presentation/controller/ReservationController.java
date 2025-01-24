package com.github.pieter_groenendijk.presentation.controller;


import com.github.pieter_groenendijk.exception.EntityNotFoundException;
import com.github.pieter_groenendijk.dto.ReservationDTO;
import com.github.pieter_groenendijk.domain.entities.loan.Loan;
import com.github.pieter_groenendijk.domain.entities.reservation.Reservation;
import com.github.pieter_groenendijk.domain.services.reservation.IReservationService;
import com.github.pieter_groenendijk.domain.services.loan.ILoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final IReservationService SERVICE;
    private final ILoanService LOAN_SERVICE;

    public ReservationController(
        ILoanService loanService,
        IReservationService service
    ) {
        this.LOAN_SERVICE = loanService;
        this.SERVICE = service;
    }

    @Operation(summary = "Create a reservation", description = "Create a new reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reservation created"),
            @ApiResponse(responseCode = "404", description = "ProductCopy or Membership not found"),
    })
    @PostMapping
    public ResponseEntity<?>  store(@RequestBody ReservationDTO reservationDTO) {
        try {
            Reservation reservation = SERVICE.store(reservationDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Get all reservation details by reservationId", description = "Get reservation details by reservationId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation found"),
            @ApiResponse(responseCode = "404", description = "No reservation found for the given reservationId\"")
    })
    @GetMapping("/{reservationId}")
    public ResponseEntity<Reservation> retrieveReservationById(@PathVariable("reservationId") long reservationId) {
        try {
            Reservation reservation = SERVICE.retrieveReservationById(reservationId);
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } catch (HibernateException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @Operation(summary = "Check if reservation is ready for pickup", description = "Check if reservation is ready for pickup by reservationId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ready for pickup status retrieved"),
            @ApiResponse(responseCode = "404", description = "Reservation not found")
    })
    @GetMapping("/ready/{reservationId}/")
    public ResponseEntity<Boolean> readyForPickup(@PathVariable("reservationId") long reservationId) {
        boolean isReady = SERVICE.readyForPickup(reservationId);
        return new ResponseEntity<>(isReady, HttpStatus.OK);
    }


    @Operation(summary = "Convert reservation to loan", description = "Change the status of the reservation to LOANED")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation converted to loan successfully"),
            @ApiResponse(responseCode = "404", description = "Reservation not found")
    })
    @PutMapping("/convertToLoan/{reservationId}")
    public ResponseEntity<String> markReservationAsLoaned(@PathVariable("reservationId") long reservationId) {
        try {
            Reservation reservation = SERVICE.retrieveReservationById(reservationId);
            if (reservation == null) {
                return new ResponseEntity<>("Reservation not found", HttpStatus.NOT_FOUND);
            }

            Loan newLoan = LOAN_SERVICE.convertReservationToLoan(reservation);

            return new ResponseEntity<>("Reservation converted to loan successfully with Loan ID: "
                    + newLoan.getLoanId(), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Cancel a reservation", description = "Change the status of the reservation to CANCELLED")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation cancelled successfully"),
            @ApiResponse(responseCode = "404", description = "Reservation not found")
    })
    @PutMapping("/{reservationId}/cancel")
    public ResponseEntity<String> cancelReservation(@PathVariable("reservationId") long reservationId) {
        try {
            Reservation reservation = SERVICE.retrieveReservationById(reservationId);
            if (reservation == null) {
                return new ResponseEntity<>("Reservation not found", HttpStatus.NOT_FOUND);
            }

            SERVICE.cancelReservation(reservationId);

            return new ResponseEntity<>("Reservation cancelled successfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}


