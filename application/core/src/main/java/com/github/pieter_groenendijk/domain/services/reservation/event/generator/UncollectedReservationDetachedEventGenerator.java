package com.github.pieter_groenendijk.domain.services.reservation.event.generator;

import com.github.pieter_groenendijk.domain.entities.reservation.Reservation;
import com.github.pieter_groenendijk.domain.entities.event.EventType;
import com.github.pieter_groenendijk.datasource.repositories.event.IEventRepository;

import java.time.LocalDateTime;

public class UncollectedReservationDetachedEventGenerator extends DetachedReservationEventGenerator {
    protected UncollectedReservationDetachedEventGenerator(IEventRepository repository) {
        super(
            repository,
            EventType.UNCOLLECTED_RESERVATION
        );
    }

    @Override
    protected LocalDateTime determineScheduledDateTime(Reservation reservation) {
        return LocalDateTime.now();
//        return TimeUtils.dateToLocalDateTime(reservation.getReservationPickUpDate())
//            .plusHours(1); // 1 hour leeway
    }
}
