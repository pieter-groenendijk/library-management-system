package com.github.pieter_groenendijk.model;

import java.time.LocalDate;


public class Reservation {

    private long reservationId;


    private LocalDate reservationDate;


    private boolean readyForPickup;


    private LocalDate reservationPickUpDate;


    private ProductCopy productCopy;


    private Membership membership;


    private ReservationStatus reservationStatus;

    // Getters and Setters
    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public boolean isReadyForPickup() {
        return readyForPickup;
    }

    public void setReadyForPickup(boolean readyForPickup) {
        this.readyForPickup = readyForPickup;
    }

    public LocalDate getReservationPickUpDate() {
        return reservationPickUpDate;
    }

    public void setReservationPickUpDate(LocalDate reservationPickUpDate) {
        this.reservationPickUpDate = reservationPickUpDate;
    }

    public ProductCopy getProductCopy() {
        return productCopy;
    }

    public void setProductCopy(ProductCopy productCopy) {
        this.productCopy = productCopy;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}