package com.pe.hampcode.controller;

import com.pe.hampcode.dto.ReservationDto;
import com.pe.hampcode.dto.ReservationResponseDto;
import com.pe.hampcode.service.ReservationService;
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
    public ResponseEntity<ReservationResponseDto> createReservation(
            @PathVariable Long restaurantId,
            @RequestBody ReservationDto reservationResource) {


        ReservationResponseDto reservationResponseResource = reservationService.createReservation(
                reservationResource.getCustomerId(),
                restaurantId,
                reservationResource.getReservationDateTime(),
                reservationResource.getNumberOfPeople()
        );



        return new ResponseEntity<>(reservationResponseResource, HttpStatus.CREATED);

    }


}
