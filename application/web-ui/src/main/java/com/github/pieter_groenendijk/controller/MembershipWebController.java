package com.github.pieter_groenendijk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class MembershipWebController {

    private final RestTemplate restTemplate;

    @Autowired
    public MembershipWebController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/membership/account")
    public String getMembershipAccount(@RequestParam("accountId") String accountId, Model model) {
        String url = "http://core:8080/membership/account/" + accountId;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            model.addAttribute("accountResponse", response.getBody());
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
