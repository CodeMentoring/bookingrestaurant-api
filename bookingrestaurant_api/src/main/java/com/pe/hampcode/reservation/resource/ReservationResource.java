package com.pe.hampcode.reservation.resource;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationResource {

    @NotBlank(message = "La fecha y hora de reserva no puede estar en blanco.")
    @Future(message = "La fecha y hora de reserva debe ser en el futuro.")
    //private LocalDateTime reservationDateTime;
    private String reservationDateTime;

    private int numberOfPeople;
    private Long customerId;
    private Long restaurantId;

    //private CustomerResource customer;
    //private RestaurantResource restaurant;
}



