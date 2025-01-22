package com.github.pieter_groenendijk.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pieter_groenendijk.DTO.LoanRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Controller
public class LoanWebController {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public LoanWebController(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }


    @GetMapping("/loan/")
    public String showLoanForm(Model model) {
        LoanRequestDTO loanRequestDTO = new LoanRequestDTO();
        loanRequestDTO.setStartDate(LocalDate.now()); //

        model.addAttribute("loan", loanRequestDTO);
        model.addAttribute("today", LocalDate.now());

        return "loan";
    }

    @PostMapping("/loan/")
    public String processLoanForm(@ModelAttribute LoanRequestDTO loanRequestDTO, Model model) {
        System.out.println("Loan submitted: " + loanRequestDTO);

        String url = "http://core:8080/loan";
        HttpEntity<LoanRequestDTO> request = new HttpEntity<>(loanRequestDTO);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

            if (response.getStatusCode() == HttpStatus.CREATED) {
                LocalDate returnDate = LocalDate.now().plusDays(7);  // Calculate return date (7 days later)

                model.addAttribute("message", "Loan successfully created!");
                model.addAttribute("returnDate", returnDate);  // Add return date to the model
                return "loan_success";
            } else {
                model.addAttribute("message", "Error occurred while creating loan.");
                return "redirect:/loan/failure";
            }
        } catch (Exception e) {
            System.out.println("Error processing loan: " + e.getMessage());
            model.addAttribute("message", "Error processing loan: " + e.getMessage());
            return "redirect:/loan/failure";
        }
    }

}
