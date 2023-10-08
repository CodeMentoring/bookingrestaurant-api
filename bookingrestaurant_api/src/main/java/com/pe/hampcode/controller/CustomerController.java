package com.pe.hampcode.controller;


import com.pe.hampcode.service.CustomerService;
import com.pe.hampcode.dto.CustomerDto;
import com.pe.hampcode.dto.CustomerResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(@Valid @RequestBody CustomerDto customerResource) {
        CustomerResponseDto responseResource = customerService.createCustomer(customerResource);
        return new ResponseEntity<>(responseResource, HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable Long customerId) {
        CustomerResponseDto customerResponseResource = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(customerResponseResource, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
        List<CustomerResponseDto> customerResponseResource = customerService.getAllCustomers();
        return new ResponseEntity<>(customerResponseResource, HttpStatus.OK);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(
            @PathVariable Long customerId,
            @Valid @RequestBody CustomerDto customerResource) {
        CustomerResponseDto customerResponseResource = customerService.updateCustomer(customerId, customerResource);
        return new ResponseEntity<>(customerResponseResource, HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
