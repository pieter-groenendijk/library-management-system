package com.github.pieter_groenendijk.repository;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class Repository {
    private static final String BASE_URL = "http://core:8080";

    private final RestTemplate REST_TEMPLATE;

    public Repository(
        RestTemplate restTemplate
    ) {
        this.REST_TEMPLATE = restTemplate;
    }

    public <T> Optional<T> retrieve(String path, Class<T> retrievedType, Object... pathVariable) {
        try {
            return Optional.ofNullable(this.REST_TEMPLATE.getForObject(
                this.generateURL(path),
                retrievedType,
                pathVariable
            ));
        } catch (RestClientException exception) { // TODO: We should probably let the caller figure out most exceptions
            System.out.println(exception);
            return Optional.empty();
        }
    }

    private String generateURL(String path) {
        return Repository.BASE_URL + path;
    }
}
