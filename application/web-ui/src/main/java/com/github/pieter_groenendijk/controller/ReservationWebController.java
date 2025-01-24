package com.github.pieter_groenendijk.controller;

import com.github.pieter_groenendijk.repositories.ReservationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class ReservationWebController {

    private final ReservationRepository repository;


    public ReservationWebController(RestTemplate restTemplate, ReservationRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{reservationId}")
    public String showReservation(
            @PathVariable("reservationId") Long reservationId,
            Model model
    ) {
        return repository.retrieve(reservationId)
                .map(reservation -> {
                    model.addAttribute("reservation", reservation);
                    model.addAttribute("reservationId", reservation.getReservationId());
                    model.addAttribute("reservationDate", reservation.getReservationDate());
                    model.addAttribute("readyForPickup", reservation.isReadyForPickup());
                    model.addAttribute("reservationPickUpDate", reservation.getReservationPickUpDate());
                    model.addAttribute("productCopy", reservation.getProductCopy());
                    model.addAttribute("membership", reservation.getMembership());
                    model.addAttribute("reservationStatus", reservation.getReservationStatus());
                    return "reservation/show";
                })
                .orElseGet(() -> "redirect:/reservations/error");
    }
}


