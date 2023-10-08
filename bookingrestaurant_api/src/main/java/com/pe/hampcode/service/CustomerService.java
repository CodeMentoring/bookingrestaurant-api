package com.pe.hampcode.service;

import com.pe.hampcode.repository.CustomerRepository;
import com.pe.hampcode.entity.Customer;
import com.pe.hampcode.mappers.CustomerMapper;
import com.pe.hampcode.dto.CustomerDto;
import com.pe.hampcode.dto.CustomerResponseDto;
import com.pe.hampcode.exception.ResourceAlreadyExistsException;
import com.pe.hampcode.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    public List<CustomerResponseDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.entityListToResponseResourceList(customers);
    }

    public CustomerResponseDto getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));
        return customerMapper.entityToResponseResource(customer);
    }

    @Transactional
    public CustomerResponseDto createCustomer(CustomerDto customerResource) {
        if (customerRepository.existsByEmail(customerResource.getEmail())) {
            throw new ResourceAlreadyExistsException("Customer with email already exists: " + customerResource.getEmail());
        }

        Customer customer = customerMapper.resourceToEntity(customerResource);
        customer = customerRepository.save(customer);

        return customerMapper.entityToResponseResource(customer);
    }

    @Transactional
    public CustomerResponseDto updateCustomer(Long customerId, CustomerDto customerResource) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();

            customer.setFirstName(customerResource.getFirstName());
            customer.setLastName(customerResource.getLastName());
            customer.setEmail(customerResource.getEmail());

            customer = customerRepository.save(customer);
            return customerMapper.entityToResponseResource(customer);
        } else {
            throw new ResourceNotFoundException("Customer not found with id: " + customerId);
        }
    }

    @Transactional
    public void deleteCustomer(Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Customer not found with id: " + customerId);
        }

        customerRepository.deleteById(customerId);
    }
}
