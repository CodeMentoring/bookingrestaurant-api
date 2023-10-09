package com.pe.hampcode.service;

import com.pe.hampcode.entity.Customer;
import com.pe.hampcode.exception.ResourceAlreadyExistsException;
import com.pe.hampcode.exception.ResourceNotFoundException;
import com.pe.hampcode.mappers.CustomerMapper;
import com.pe.hampcode.repository.CustomerRepository;
import com.pe.hampcode.dto.CustomerDto;
import com.pe.hampcode.dto.CustomerResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCustomers() {
        // Arrange
        List<Customer> customers = new ArrayList<>();
        List<CustomerResponseDto> responseDtoList = new ArrayList<>();

        when(customerRepository.findAll()).thenReturn(customers);
        when(customerMapper.entityListToResponseResourceList(customers)).thenReturn(responseDtoList);

        // Act
        List<CustomerResponseDto> result = customerService.getAllCustomers();

        // Assert
        assertEquals(responseDtoList, result);
    }

    @Test
    public void testGetCustomerById() {
        // Arrange
        Long customerId = 1L;
        Customer customer = new Customer();
        CustomerResponseDto responseDto = new CustomerResponseDto();

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerMapper.entityToResponseResource(customer)).thenReturn(responseDto);

        // Act
        CustomerResponseDto result = customerService.getCustomerById(customerId);

        // Assert
        assertEquals(responseDto, result);
    }

    @Test
    public void testGetCustomerByIdNotFound() {
        // Arrange
        Long nonExistentCustomerId = 999L;

        when(customerRepository.findById(nonExistentCustomerId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            customerService.getCustomerById(nonExistentCustomerId);
        });
    }

    @Test
    public void testCreateCustomer() {
        // Arrange
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName("John");
        customerDto.setLastName("Doe");
        customerDto.setEmail("john.doe@example.com");

        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());

        CustomerResponseDto responseDto = new CustomerResponseDto();
        responseDto.setId(1L);
        responseDto.setFirstName(customerDto.getFirstName());
        responseDto.setLastName(customerDto.getLastName());
        responseDto.setEmail(customerDto.getEmail());

        when(customerRepository.existsByEmail(customerDto.getEmail())).thenReturn(false);
        when(customerMapper.resourceToEntity(customerDto)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.entityToResponseResource(customer)).thenReturn(responseDto);

        // Act
        CustomerResponseDto result = customerService.createCustomer(customerDto);

        // Assert
        assertEquals(responseDto, result);
    }


    @Test
    public void testCreateCustomerAlreadyExists() {
        // Arrange
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName("John");
        customerDto.setLastName("Doe");
        customerDto.setEmail("john.doe@example.com");

        when(customerRepository.existsByEmail(customerDto.getEmail())).thenReturn(true);

        // Act and Assert
        assertThrows(ResourceAlreadyExistsException.class, () -> {
            customerService.createCustomer(customerDto);
        });
    }


    @Test
    public void testUpdateCustomer() {
        // Arrange
        Long customerId = 1L;
        CustomerDto customerDto = new CustomerDto();
        Customer customer = new Customer();
        CustomerResponseDto responseDto = new CustomerResponseDto();

        // Configura el objeto customer con datos de ejemplo
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");



        // Configura el comportamiento esperado de los mocks
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any())).thenReturn(customer); // Cambia esto si es necesario
        when(customerMapper.entityToResponseResource(customer)).thenReturn(responseDto);

        // Act
        CustomerResponseDto result = customerService.updateCustomer(customerId, customerDto);

        // Assert
        assertNotNull(result); // Asegúrate de que result no sea nulo
        assertEquals(responseDto, result); // Asegúrate de que result sea igual a responseDto
    }





    @Test
    public void testUpdateCustomerNotFound() {
        // Arrange
        Long nonExistentCustomerId = 999L;
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName("UpdatedFirstName");
        customerDto.setLastName("UpdatedLastName");
        customerDto.setEmail("updated.email@example.com");

        when(customerRepository.findById(nonExistentCustomerId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            customerService.updateCustomer(nonExistentCustomerId, customerDto);
        });
    }

    @Test
    public void testDeleteCustomer() {
        // Arrange
        Long customerId = 1L;

        when(customerRepository.existsById(customerId)).thenReturn(true);

        // Act
        customerService.deleteCustomer(customerId);

        // Assert
        verify(customerRepository, times(1)).deleteById(customerId);
    }

    @Test
    public void testDeleteCustomerNotFound() {
        // Arrange
        Long nonExistentCustomerId = 999L;

        when(customerRepository.existsById(nonExistentCustomerId)).thenReturn(false);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            customerService.deleteCustomer(nonExistentCustomerId);
        });
    }
}
