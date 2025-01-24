package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Reservation;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.lang.annotation.Annotation;
import java.util.Optional;

@Repository
public class ReservationRepository extends com.github.pieter_groenendijk.repository.Repository {
    private final String BASE_PATH = "/reservations";

    public ReservationRepository(RestTemplate restTemplate) {
        super(restTemplate);
    }
    public Optional<Reservation> retrieve(Long reservationId) {
        return super.getSingle(
                BASE_PATH + "/{reservationId}",
                Reservation.class,
                reservationId
        );
    }
}
