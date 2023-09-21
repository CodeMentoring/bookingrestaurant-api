package com.pe.hampcode.customer.mappers;

import com.pe.hampcode.customer.domain.entity.Customer;
import com.pe.hampcode.customer.resource.CustomerResource;
import com.pe.hampcode.customer.resource.CustomerResponseResource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CustomerMapper {
    private final ModelMapper modelMapper;

    public CustomerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Customer resourceToEntity(CustomerResource customerResource) {
        return modelMapper.map(customerResource, Customer.class);
    }

    public CustomerResource entityToResource(Customer customer) {
        return modelMapper.map(customer, CustomerResource.class);
    }

    public CustomerResponseResource entityToResponseResource(Customer customer) {
        return modelMapper.map(customer, CustomerResponseResource.class);
    }

    public List<Customer> resourceListToEntityList(List<CustomerResource> customerResources) {
        return customerResources
                .stream()
                .map(this::resourceToEntity)
                .toList();
    }

    public List<CustomerResource> entityListToResourceList(List<Customer> customers) {
        return customers
                .stream()
                .map(this::entityToResource)
                .toList();
    }

    public List<CustomerResponseResource> entityListToResponseResourceList(List<Customer> customers) {
        return customers
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }

}
