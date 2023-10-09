package com.pe.hampcode.controller;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.pe.hampcode.dto.CustomerDto;
import com.pe.hampcode.dto.CustomerResponseDto;
import com.pe.hampcode.exception.ResourceNotFoundException;
import com.pe.hampcode.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCustomer() throws Exception {
        CustomerDto customerDto = new CustomerDto();
        // Definir cualquier objeto necesario en customerDto

        CustomerResponseDto responseDto = new CustomerResponseDto();
        // Definir cualquier objeto necesario en responseDto

        when(customerService.createCustomer(customerDto)).thenReturn(responseDto);

        ResponseEntity<CustomerResponseDto> responseEntity = customerController.createCustomer(customerDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(responseDto, responseEntity.getBody());
    }

    @Test
    public void testGetCustomerById() throws Exception {
        Long customerId = 1L;
        CustomerResponseDto responseDto = new CustomerResponseDto();
        // Definir cualquier objeto necesario en responseDto

        when(customerService.getCustomerById(customerId)).thenReturn(responseDto);

        ResponseEntity<CustomerResponseDto> responseEntity = customerController.getCustomerById(customerId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDto, responseEntity.getBody());
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        List<CustomerResponseDto> responseDtoList = new ArrayList<>();
        // Agregar objetos CustomerResponseDto a responseDtoList

        when(customerService.getAllCustomers()).thenReturn(responseDtoList);

        ResponseEntity<List<CustomerResponseDto>> responseEntity = customerController.getAllCustomers();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDtoList, responseEntity.getBody());
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        Long customerId = 1L;
        CustomerDto customerDto = new CustomerDto();
        // Definir cualquier objeto necesario en customerDto

        CustomerResponseDto responseDto = new CustomerResponseDto();
        // Definir cualquier objeto necesario en responseDto

        when(customerService.updateCustomer(customerId, customerDto)).thenReturn(responseDto);

        ResponseEntity<CustomerResponseDto> responseEntity = customerController.updateCustomer(customerId, customerDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDto, responseEntity.getBody());
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        Long customerId = 1L;

        ResponseEntity<Void> responseEntity = customerController.deleteCustomer(customerId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(customerService, times(1)).deleteCustomer(customerId);
    }
}
