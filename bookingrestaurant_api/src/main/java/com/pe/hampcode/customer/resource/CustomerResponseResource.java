package com.pe.hampcode.customer.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseResource {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}