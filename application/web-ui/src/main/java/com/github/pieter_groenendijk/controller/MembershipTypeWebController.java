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
import com.github.pieter_groenendijk.model.MembershipType;
import com.github.pieter_groenendijk.DTO.MembershipRequestDTO;
import com.github.pieter_groenendijk.DTO.MembershipTypeRequestDTO;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;


@Controller
public class MembershipTypeWebController {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public MembershipTypeWebController(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/membershiptype/")
    public String showMembershipForm() {
        return "membershiptype";
    }

    @PostMapping("/membershiptype/getAll")
    public String getAllMembershipTypes(Model model) {
        String url = "http://core:8080/membershiptype/getAll";  // No parameter required

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                // Deserialize the response to a list of MembershipType objects
                List<MembershipType> membershipTypes = objectMapper.readValue(response.getBody(), new TypeReference<List<MembershipType>>() {});
                model.addAttribute("membershipTypes", membershipTypes);
            } else {
                model.addAttribute("error2", "Failed to retrieve membership types.");
            }
        } catch (Exception e) {
            model.addAttribute("error2", "Something went wrong... Try again!");
        }
        return "membershipType"; // Return the page where you want to display membership types
    }
}