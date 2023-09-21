package com.pe.hampcode.reservation.resource;

import com.pe.hampcode.customer.resource.CustomerResponseResource;
import com.pe.hampcode.restaurant.resource.RestaurantResponseResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseResource {
    private Long id;
    private LocalDateTime reservationDateTime;
    private int numberOfPeople;
    private CustomerResponseResource customer;
    private RestaurantResponseResource restaurant;
}
