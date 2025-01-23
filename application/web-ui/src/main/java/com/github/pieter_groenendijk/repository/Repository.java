package com.github.pieter_groenendijk.repository;

import org.springframework.http.HttpEntity;
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

    public <T> Optional<T> getSingle(
        String path,
        Class<T> retrievedType,
        Object... pathVariable
    ) {
        return Optional.ofNullable(this.get(
            path,
            retrievedType,
            pathVariable
        ));
    }

    public <T> List<T> getList(
        String path,
        Class<T[]> listType,
        Object... pathVariables
    ) {
        T[] array = this.get(
            path,
            listType,
            pathVariables
        );

        if (array == null) return List.of(); // Needed: Otherwise throws NullPointerException

        return List.of(array);
    }

    // TODO: Think of better name
    private <T> T get(
        String path,
        Class<T> retrievedType,
        Object... pathVariables
    ) {
        try {
            return tryGet(path, retrievedType, pathVariables);
        } catch (RestClientException exception) {
            this.handleException(exception);
            return null;
        }
    }

    private <T> T tryGet(
        String path,
        Class<T> retrievedType,
        Object... pathVariables
    ) {
        return this.REST_TEMPLATE.getForObject(
            this.generateURL(path),
            retrievedType,
            pathVariables
        );
    }

    public <T> Optional<T> postSingle(
        String path,
        HttpEntity<?> request,
        Class<T> retrievedType,
        Object... pathVariables
    ) {
        return Optional.ofNullable(
            this.post(
                path,
                request,
                retrievedType,
                pathVariables
            )
        );
    }

    public void postSingle(
        String path,
        HttpEntity<?> request,
        Object... pathVariables
    ) {
        this.post(
            path,
            request,
            Void.class,
            pathVariables
        );
    }

    public void postSingle(
        String path,
        Object... pathVariables
    ) {
        this.post(
            path,
            null,
            Void.class,
            pathVariables
        );
    }

    public <T> T postSingle(
        String path,
        Class<T> retrievedType,
        Object... pathVariables
    ) {
        return this.post(
            path,
            null,
            retrievedType,
            pathVariables
        );
    }

    private <T> T post(
        String path,
        HttpEntity<?> request,
        Class<T> retrievedType,
        Object... pathVariables
    ) {
        try {
            return this.tryPost(path, request, retrievedType, pathVariables);
        } catch (RestClientException exception) {
            this.handleException(exception);
            return null;
        }
    }

    private <T> T tryPost(
        String path,
        HttpEntity<?> request,
        Class<T> retrievedType,
        Object... pathVariables
    ) {
        return this.REST_TEMPLATE.postForObject(
            this.generateURL(path),
            request,
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
