package com.github.pieter_groenendijk.repository.fines;

import com.github.pieter_groenendijk.repository.Repository;
import org.springframework.web.client.RestTemplate;

@org.springframework.stereotype.Repository
public class FineRepository extends Repository {
    public FineRepository(RestTemplate restTemplate) {
        super(restTemplate);
    }


}
