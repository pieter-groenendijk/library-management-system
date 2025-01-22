package com.github.pieter_groenendijk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.http.HttpMethod;
import java.util.List;
import com.github.pieter_groenendijk.model.Membership;
import com.github.pieter_groenendijk.DTO.MembershipRequestDTO;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;


@Controller
public class MembershipWebController {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public MembershipWebController(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/membership/account")
    public String getMembershipAccount(@RequestParam("accountId") String accountId, Model model) {
        String url = "http://core:8080/membership/account/" + accountId;

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                List<Membership> memberships = objectMapper.readValue(response.getBody(), new TypeReference<List<Membership>>() {});
                model.addAttribute("memberships", memberships);
            } else  {
                model.addAttribute("error1", "Account not found. Please check the Account ID.");
            }
        } catch (Exception e) {
            model.addAttribute("error1", "Something went wrong...Try again!");
        }
        return "membership"; // Return to the same page with an error message
    }

    @GetMapping("/membership/")
    public String showMembershipForm() {
        return "membership";
    }

    @PostMapping("/membership/add")
    public String addMembership(@RequestParam("account") Long accountId,
                                @RequestParam("membershipTypeId") Long membershipTypeId,
                                Model model) {
        String url = "http://core:8080/membership";
        MembershipRequestDTO requestDTO = new MembershipRequestDTO();
        requestDTO.setAccountId(accountId);
        requestDTO.setMembershipTypeId(membershipTypeId);

        try {
            HttpEntity<MembershipRequestDTO> request = new HttpEntity<>(requestDTO);
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                model.addAttribute("success", true);
            } else {
                model.addAttribute("error", "Failed to create membership.");
            }
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred: ");
        }
        return "membership";
    }

    @PostMapping("/membership/softdelete")
    public String deleteMembership(@RequestParam("deleteId") Long membershipId, Model model) {
        String url = "http://core:8080/membership/softdelete/" + membershipId;

        try {
            // Use PUT request to soft delete membership
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, null, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                model.addAttribute("success2", "Membership deleted successfully.");
            } else {
                model.addAttribute("error2", "Membership not deleted. Please check the Membership ID.");
            }
        } catch (Exception e) {
            model.addAttribute("error2", "Something went wrong... Try again!");
        }

        return "membership"; // Return to the same page with an error message
    }
}
