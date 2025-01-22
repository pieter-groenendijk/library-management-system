package com.github.pieter_groenendijk.controller;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pieter_groenendijk.DTO.LoanRequestDTO;
import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.Membership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

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

        model.addAttribute("loanRequestDTO", loanRequestDTO);
        model.addAttribute("today", LocalDate.now());

        return "loan";
    }

    @PostMapping("/loan/store")
    public String processLoanForm(@ModelAttribute LoanRequestDTO loanRequestDTO, Model model) {
        System.out.println("Loan submitted: " + loanRequestDTO);


        try {
            String url = "http://core:8080/loan";
            HttpEntity<LoanRequestDTO> request = new HttpEntity<>(loanRequestDTO);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

            if (response.getStatusCode() == HttpStatus.CREATED) {
                LocalDate returnDate = LocalDate.now().plusDays(7);


                return "redirect:/loan/success";
            } else {
                return "redirect:/loan/failure";
            }
        } catch (Exception e) {
            System.out.println("Error processing loan: " + e.getMessage());

            return "redirect:/loan/failure";
        }
    }

    @GetMapping("/loan/{loanId}")
    public String retrieveLoanByLoanId(@PathVariable("loanId") long loanId, Model model) {
        String url = "http://core:8080/loan/" + loanId;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            try {
                Loan loan = objectMapper.readValue(response.getBody(), Loan.class);
                model.addAttribute("loanId", loan);
            } catch (Exception e) {
                model.addAttribute("error", "Error Parsing the response");
            }
        } else {
            model.addAttribute("loanResponse", "Error fetching loan details.");
        }

        return "loan";
    }
}


