package com.pe.hampcode.dto;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {

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



