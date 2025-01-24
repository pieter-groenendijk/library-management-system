package com.github.pieter_groenendijk.domain.services.reservation;

import com.github.pieter_groenendijk.dto.ReservationDTO;
import com.github.pieter_groenendijk.domain.entities.membership.Membership;
import com.github.pieter_groenendijk.domain.entities.reservation.Reservation;
import com.github.pieter_groenendijk.domain.entities.product.ProductCopy;

import java.time.LocalDate;
import java.util.List;


public interface IReservationService {
    Reservation store(ReservationDTO reservation) throws Exception;
    Reservation retrieveReservationById(long reservationId);
    List<Reservation> reservation(long membershipId);
    Reservation updateReservation(Reservation reservation);
    void cancelReservation(long reservationId);
    boolean readyForPickup(long reservationId);
    LocalDate generateReservationPickUpDate(ProductCopy productCopy);
    void handleUncollectedReservations(long membershipId, LocalDate currentDate) throws Exception;
    void markReservationAsLoaned(long reservationId);
    void handleProductCopyAvailability(ProductCopy productCopy);

    Reservation toEntity(ReservationDTO dto, ProductCopy productCopy, Membership membership);
}
