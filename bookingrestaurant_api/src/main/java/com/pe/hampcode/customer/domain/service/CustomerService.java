package com.pe.hampcode.customer.domain.service;

import com.pe.hampcode.customer.domain.entity.Customer;
import com.pe.hampcode.customer.domain.persistence.CustomerRepository;
import com.pe.hampcode.customer.mappers.CustomerMapper;
import com.pe.hampcode.customer.resource.CustomerResource;
import com.pe.hampcode.customer.resource.CustomerResponseResource;
import com.pe.hampcode.shared.exception.ResourceAlreadyExistsException;
import com.pe.hampcode.shared.exception.ResourceNotFoundException;
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


    public List<CustomerResponseResource> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.entityListToResponseResourceList(customers);
    }

    public CustomerResponseResource getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));
        return customerMapper.entityToResponseResource(customer);
    }

    @Transactional
    public CustomerResponseResource createCustomer(CustomerResource customerResource) {
        if (customerRepository.existsByEmail(customerResource.getEmail())) {
            throw new ResourceAlreadyExistsException("Customer with email already exists: " + customerResource.getEmail());
        }

        Customer customer = customerMapper.resourceToEntity(customerResource);
        customer = customerRepository.save(customer);

        return customerMapper.entityToResponseResource(customer);
    }

    @Transactional
    public CustomerResponseResource updateCustomer(Long customerId, CustomerResource customerResource) {
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
