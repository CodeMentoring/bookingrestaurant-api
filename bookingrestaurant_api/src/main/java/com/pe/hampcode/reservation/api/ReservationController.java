package com.pe.hampcode.reservation.api;

import com.pe.hampcode.customer.resource.CustomerResource;
import com.pe.hampcode.customer.resource.CustomerResponseResource;
import com.pe.hampcode.reservation.domain.entity.Reservation;
import com.pe.hampcode.reservation.domain.service.ReservationService;
import com.pe.hampcode.reservation.mappers.ReservationMapper;
import com.pe.hampcode.reservation.resource.ReservationResource;
import com.pe.hampcode.reservation.resource.ReservationResponseResource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    //private final ReservationMapper reservationMapper;



    @PostMapping("/restaurants/{restaurantId}")
    public ResponseEntity<ReservationResponseResource> createReservation(
            @PathVariable Long restaurantId,
            @RequestBody ReservationResource reservationResource) {


        ReservationResponseResource reservationResponseResource = reservationService.createReservation(
                reservationResource.getCustomerId(),
                restaurantId,
                reservationResource.getReservationDateTime(),
                reservationResource.getNumberOfPeople()
        );



        return new ResponseEntity<>(reservationResponseResource, HttpStatus.CREATED);

    }


}
