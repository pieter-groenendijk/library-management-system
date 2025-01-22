package com.github.pieter_groenendijk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import com.github.pieter_groenendijk.model.Membership;

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
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            try {
                List<Membership> memberships = objectMapper.readValue(response.getBody(), new TypeReference<List<Membership>>() {});
                model.addAttribute("memberships", memberships);
            } catch (Exception e) {
                model.addAttribute("error", "Error Parsing the response");
            }
        } else {
            model.addAttribute("accountResponse", "Error fetching account details.");
        }

        return "membership";
    }



        @GetMapping("/membership/")
        public String showMembershipForm() {
            return "membership";
        }

}
