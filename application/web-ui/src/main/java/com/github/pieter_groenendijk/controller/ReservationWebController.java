package com.github.pieter_groenendijk.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Controller
public class ReservationWebController {

    private final RestTemplate restTemplate;
   ;

    public ReservationWebController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/create")
    public String showCreateReservationForm(Model model) {
        model.addAttribute("ReservationDTO", new ReservationDTO());
        return "create-reservation"; //TODO: Maybe just a Reservation page with the form on it?
    }

    @PostMapping("/")
    public String createReservation(@Valid @ModelAttribute("ReservationDTO") ReservationDTO reservationDTO, Model model) {
                                    String apiUrl = "http://localhost:8080/api/reservation";
    try {
        ResponseEntity<ReservationDTO> response = restTemplate.postForEntity(apiUrl, reservationDTO, ReservationDTO.class);
        model.addAttribute("reservation");
        return "reservationDetails";
    } catch (
    RestClientException e) {
        model.addAttribute("error", "Unable to create reservation. Please try again.");
        return "create-reservation";
    }
}


    @GetMapping("/{reservationId}")
    public String viewReservationDetails(@PathVariable("reservationId") long reservationId, Model model) {
        try {
            String url = "http://localhost:8081/api/reservation/" + reservationId;
            ResponseEntity<Reservation> response = restTemplate.getForEntity(url, Reservation.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                model.addAttribute("reservation");
                return "reservation-details";
            } else {
                model.addAttribute("error", "Reservation not found");
                return "error";
            }
        } catch (Exception e) {
            System.out.println("Error fetching reservation details: " + e.getMessage());
            model.addAttribute("error", "Error fetching reservation details");
            return "error";
        }
    }


    @GetMapping("/{reservationId}/cancel")
    public String cancelReservation(@PathVariable("reservationId") long reservationId, Model model) {
        try {
            String url = "http://localhost:8081/api/reservation/" + reservationId + "/cancel";
            HttpEntity<Void> request = new HttpEntity<>(null);
            ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, request, Void.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                model.addAttribute("message", "Reservation cancelled successfully!");
                return "redirect:/reservations";
            } else {
                model.addAttribute("error", "Failed to cancel reservation");
                return "error";
            }
        } catch (Exception e) {
            System.out.println("Error cancelling reservation: " + e.getMessage());
            model.addAttribute("error", "Error cancelling reservation");
            return "error";
        }
    }

    @GetMapping("/{reservationId}/convertToLoan")
    public String convertReservationToLoan(@PathVariable("reservationId") long reservationId, Model model) {
        try {
            String url = "http://localhost:8081/api/reservation/" + reservationId + "/convertToLoan";
            HttpEntity<Void> request = new HttpEntity<>(null);
            ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, request, Void.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                model.addAttribute("message", "Reservation converted to loan successfully!");
                return "redirect:/reservations";
            } else {
                model.addAttribute("error", "Failed to convert reservation to loan");
                return "error";
            }
        } catch (Exception e) {
            System.out.println("Error converting reservation to loan: " + e.getMessage());
            model.addAttribute("error", "Error converting reservation to loan");
            return "error";
        }
    }
}
