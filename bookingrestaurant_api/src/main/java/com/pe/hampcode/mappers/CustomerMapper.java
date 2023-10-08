package com.pe.hampcode.mappers;

import com.pe.hampcode.dto.CustomerDto;
import com.pe.hampcode.dto.CustomerResponseDto;
import com.pe.hampcode.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CustomerMapper {
    private final ModelMapper modelMapper;

    public CustomerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Customer resourceToEntity(CustomerDto customerResource) {
        return modelMapper.map(customerResource, Customer.class);
    }

    public CustomerDto entityToResource(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }

    public CustomerResponseDto entityToResponseResource(Customer customer) {
        return modelMapper.map(customer, CustomerResponseDto.class);
    }

    public List<Customer> resourceListToEntityList(List<CustomerDto> customerResources) {
        return customerResources
                .stream()
                .map(this::resourceToEntity)
                .toList();
    }

    public List<CustomerDto> entityListToResourceList(List<Customer> customers) {
        return customers
                .stream()
                .map(this::entityToResource)
                .toList();
    }

    public List<CustomerResponseDto> entityListToResponseResourceList(List<Customer> customers) {
        return customers
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }

}
