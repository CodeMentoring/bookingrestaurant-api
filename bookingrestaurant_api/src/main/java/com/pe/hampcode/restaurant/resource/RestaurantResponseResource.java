package com.pe.hampcode.restaurant.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponseResource {
    private Long id;
    private String name;
    private String address;
}