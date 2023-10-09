package com.pe.hampcode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseDto {
    private Long id;
    private String reservationDateTime;
    private int numberOfPeople;
    private CustomerResponseDto customer;
    private RestaurantResponseDto restaurant;
}
