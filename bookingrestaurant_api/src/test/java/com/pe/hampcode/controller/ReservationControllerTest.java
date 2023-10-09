package com.pe.hampcode.controller;
import com.pe.hampcode.dto.ReservationDto;
import com.pe.hampcode.dto.ReservationResponseDto;
import com.pe.hampcode.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class ReservationControllerTest {

    @InjectMocks
    private ReservationController reservationController;

    @Mock
    private ReservationService reservationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateReservation() {
        // Mock input data
        Long restaurantId = 1L;
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setCustomerId(2L);
        reservationDto.setReservationDateTime("2022-09-19T14:33:30");
        reservationDto.setNumberOfPeople(4);

        // Mock the response from the service
        ReservationResponseDto mockResponse = new ReservationResponseDto();
        mockResponse.setId(1L);
        mockResponse.setReservationDateTime(reservationDto.getReservationDateTime());
        mockResponse.setNumberOfPeople(reservationDto.getNumberOfPeople());
        mockResponse.setCustomer(null); // You can set the customer if needed
        mockResponse.setRestaurant(null); // You can set the restaurant if needed

        when(reservationService.createReservation(
                reservationDto.getCustomerId(),
                restaurantId,
                reservationDto.getReservationDateTime(),
                reservationDto.getNumberOfPeople()
        )).thenReturn(mockResponse);

        // Perform the test
        ResponseEntity<ReservationResponseDto> responseEntity = reservationController
                .createReservation(restaurantId, reservationDto);

        // Verify the service method was called
        /*verify(reservationService, times(1)).createReservation(
                reservationDto.getCustomerId(),
                restaurantId,
                reservationDto.getReservationDateTime(),
                reservationDto.getNumberOfPeople()
        );*/

        // Assertions
        assert responseEntity.getStatusCode() == HttpStatus.CREATED;
        assert responseEntity.getBody() != null;
        assert responseEntity.getBody().getId() != null;
        // Add more assertions as needed
    }
}
