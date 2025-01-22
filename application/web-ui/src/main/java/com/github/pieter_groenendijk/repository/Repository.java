package com.github.pieter_groenendijk.repository;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

public class Repository {
    private static final String BASE_URL = "http://core:8080";

    private final RestTemplate REST_TEMPLATE;

    public Repository(
        RestTemplate restTemplate
    ) {
        this.REST_TEMPLATE = restTemplate;
    }

    public <T> Optional<T> retrieveSingle(String path, Class<T> retrievedType, Object... pathVariable) {
        return Optional.ofNullable(this.retrieve(
            path,
            retrievedType,
            pathVariable
        ));
    }

    public <T> List<T> retrieveList(String path, Class<T[]> listType, Object... pathVariables) {
        T[] array = this.retrieve(
            path,
            listType,
            pathVariables
        );

        if (array == null) return List.of(); // Needed: Otherwise throws NullPointerException

        return List.of(array);
    }

    // TODO: Think of better name
    private <T> T retrieve(String path, Class<T> retrievedType, Object... pathVariables) {
        try {
            return tryRetrieve(path, retrievedType, pathVariables);
        } catch (RestClientException exception) {
            this.handleException(exception);
            return null;
        }
    }

    private <T> T tryRetrieve(String path, Class<T> retrievedType, Object... pathVariables) {
        return this.REST_TEMPLATE.getForObject(
            this.generateURL(path),
            retrievedType,
            pathVariables
        );
    }

    private void handleException(Exception exception) {
        exception.printStackTrace();
    }

    private String generateURL(String path) {
        return Repository.BASE_URL + path;
    }
}
