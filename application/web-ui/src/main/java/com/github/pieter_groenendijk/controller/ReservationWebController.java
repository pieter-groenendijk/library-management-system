package com.github.pieter_groenendijk.controller;
import com.github.pieter_groenendijk.model.DTO.ReservationDTO;
import com.github.pieter_groenendijk.model.Reservation;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

public class ReservationWebController {

    private final RestTemplate restTemplate;

    public ReservationWebController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/create")
    public String showCreateReservationForm(Model model) {
        model.addAttribute("reservationDTO", new ReservationDTO());
        return "create-reservation"; //TODO: Maybe just a Reservation page with the form on it?
    }

    @PostMapping("/create")
    public String createReservation(@Valid @ModelAttribute("reservationDTO") ReservationDTO reservationDTO,
                                    BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create-reservation";
        }

        try {
            String url = "http://localhost:8080/api/reservations";
            HttpEntity<ReservationDTO> request = new HttpEntity<>(reservationDTO);
            ResponseEntity<Void> response = restTemplate.postForEntity(url, request, Void.class);

            if (response.getStatusCode() == HttpStatus.CREATED) {
                model.addAttribute("message", "Reservation created successfully!");
                return "redirect:/reservations"; //TODO Create reservations page
            } else {
                model.addAttribute("error", "Failed to create reservation");
                return "create-reservation"; //TODO: Create error page
            }
        } catch (Exception e) {
            System.out.println("Error creating reservation: " + e.getMessage());
            model.addAttribute("error", "Error creating reservation");
            return "error"; //TODO: Create error page
        }
    }



    @GetMapping("/{reservationId}")
    public String viewReservationDetails(@PathVariable("reservationId") long reservationId, Model model) {
        try {
            String url = "http://localhost:8080/api/reservations/" + reservationId;
            ResponseEntity<Reservation> response = restTemplate.getForEntity(url, Reservation.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                model.addAttribute("reservation", response.getBody());
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
            String url = "http://localhost:8080/api/reservations/" + reservationId + "/cancel";
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
            String url = "http://localhost:8080/api/reservations/" + reservationId + "/convertToLoan";
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
