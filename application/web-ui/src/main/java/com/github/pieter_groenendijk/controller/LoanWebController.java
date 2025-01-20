package com.github.pieter_groenendijk.controller;
import com.github.pieter_groenendijk.controller.LoanController;
import com.github.pieter_groenendijk.model.DTO.LoanRequestDTO;
import com.github.pieter_groenendijk.repository.loan.ILoanRepository;
import com.github.pieter_groenendijk.service.loan.ILoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

public class LoanWebController {

    private final LoanController loanController;


    public LoanWebController(LoanController loanController, ILoanService loanService, ILoanRepository loanRepository) {
        this.loanController = loanController;
        ;
    }


    @GetMapping("/loan")
    public String showLoanForm(Model model) {
        LoanRequestDTO loanRequestDTO = new LoanRequestDTO();
        loanRequestDTO.setStartDate(LocalDate.now()); //

        model.addAttribute("loan", loanRequestDTO);
        model.addAttribute("today", LocalDate.now());

        return "loan";
    }

    @PostMapping("/loan")
    public String processLoanForm(@ModelAttribute LoanRequestDTO loanRequestDTO) {
        System.out.println("Loan submitted: " + loanRequestDTO);

        try {
            ResponseEntity response = loanController.store(loanRequestDTO);

            if (response.getStatusCode() == HttpStatus.CREATED) {
                return "redirect:/loan/success";
            } else {
                return "redirect:/loan/failure";
            }
        } catch (Exception e) {
            return "redirect:/loan/failure";
        }
    }

}
