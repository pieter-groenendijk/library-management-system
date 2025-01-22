package com.github.pieter_groenendijk.controller;
import com.github.pieter_groenendijk.DTO.LoanRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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

    public LoanWebController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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
    public String processLoanForm(@ModelAttribute LoanRequestDTO loanRequestDTO) {
        System.out.println("Loan submitted: " + loanRequestDTO);

        try {
            String url = "http://core:8080/loan/store";
            HttpEntity<LoanRequestDTO> request = new HttpEntity<>(loanRequestDTO);
            ResponseEntity<Void> response = restTemplate.postForEntity(url, request, Void.class);

            if (response.getStatusCode() == HttpStatus.CREATED) {
                return "redirect:/loan/success";
            } else {
                return "redirect:/loan/failure";
            }
        } catch (Exception e) {
            System.out.println("Error processing loan: " + e.getMessage());
            return "redirect:/loan/failure";
        }
    }

}
