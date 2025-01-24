package com.github.pieter_groenendijk.domain.entities.event;

import com.github.pieter_groenendijk.domain.entities.Reservation;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("reservation")
public class ReservationEvent extends Event<Reservation> {
    @ManyToOne
    @JoinColumn(
        name = "reservation",
        nullable = false
    )
    private Reservation reservation;

    public ReservationEvent() {}

    @Override
    public Reservation getAssociation() {
        return this.getReservation();
    }

    @Override
    public void setAssociation(Reservation reservation) {
        this.setReservation(reservation);
    }

    public Reservation getReservation() {
        return this.reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
