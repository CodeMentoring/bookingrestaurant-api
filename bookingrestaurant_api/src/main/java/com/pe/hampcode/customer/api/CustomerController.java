package com.pe.hampcode.customer.api;


import com.pe.hampcode.customer.domain.service.CustomerService;
import com.pe.hampcode.customer.resource.CustomerResource;
import com.pe.hampcode.customer.resource.CustomerResponseResource;
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
    public ResponseEntity<CustomerResponseResource> createCustomer(@Valid @RequestBody CustomerResource customerResource) {
        CustomerResponseResource responseResource = customerService.createCustomer(customerResource);
        return new ResponseEntity<>(responseResource, HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseResource> getCustomerById(@PathVariable Long customerId) {
        CustomerResponseResource customerResponseResource = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(customerResponseResource, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseResource>> getAllCustomers() {
        List<CustomerResponseResource> customerResponseResource = customerService.getAllCustomers();
        return new ResponseEntity<>(customerResponseResource, HttpStatus.OK);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResponseResource> updateCustomer(
            @PathVariable Long customerId,
            @Valid @RequestBody CustomerResource customerResource) {
        CustomerResponseResource customerResponseResource = customerService.updateCustomer(customerId, customerResource);
        return new ResponseEntity<>(customerResponseResource, HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
