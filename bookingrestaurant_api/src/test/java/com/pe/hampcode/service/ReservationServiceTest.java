package com.pe.hampcode.service;

import com.pe.hampcode.dto.ReservationResponseDto;
import com.pe.hampcode.entity.Customer;
import com.pe.hampcode.entity.Reservation;
import com.pe.hampcode.entity.Restaurant;
import com.pe.hampcode.exception.ResourceAlreadyExistsException;
import com.pe.hampcode.exception.ResourceNotFoundException;
import com.pe.hampcode.mappers.ReservationMapper;
import com.pe.hampcode.repository.CustomerRepository;
import com.pe.hampcode.repository.ReservationRepository;
import com.pe.hampcode.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReservationServiceTest {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private ReservationMapper reservationMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateReservation() {
        //Arrange (Configuración): Prepara el contexto y los objetos necesarios para la prueba.
        Long customerId = 1L;
        Long restaurantId = 2L;
        String dateTimeString = "2023-10-10T12:00:00";
        int numberOfPeople = 4;

        Customer customer = new Customer();
        customer.setId(customerId);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);

        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setRestaurant(restaurant);
        reservation.setReservationDateTime(LocalDateTime.parse(dateTimeString));
        reservation.setNumberOfPeople(numberOfPeople);

        ReservationResponseDto responseDto = new ReservationResponseDto();

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));
        when(reservationRepository.existsByReservationDateTimeAndRestaurant(reservation.getReservationDateTime(), restaurant))
                .thenReturn(false);
        when(reservationMapper.entityToResponseResource(reservation)).thenReturn(responseDto);

        // Act (Actuación): Ejecuta la acción o el código que estás probando. realizas la acción o el comportamiento que deseas probar.
        ReservationResponseDto result = reservationService.createReservation(customerId, restaurantId, dateTimeString, numberOfPeople);

        // Assertt (Afirmación): Verifica que los resultados obtenidos sean correctos y cumplan con las expectativas.
        assertNotNull(result);
        assertEquals(responseDto, result);
    }

    @Test
    public void testCreateReservationCustomerNotFound() {
        // Arrange
        Long customerId = 1L;
        Long restaurantId = 2L;
        String dateTimeString = "2023-10-10T12:00:00";
        int numberOfPeople = 4;

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class,
                () -> reservationService.createReservation(customerId, restaurantId, dateTimeString, numberOfPeople));
    }

    @Test
    public void testCreateReservationRestaurantNotFound() {
        // Arrange
        Long customerId = 1L;
        Long restaurantId = 2L;
        String dateTimeString = "2023-10-10T12:00:00";
        int numberOfPeople = 4;

        Customer customer = new Customer();
        customer.setId(customerId);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class,
                () -> reservationService.createReservation(customerId, restaurantId, dateTimeString, numberOfPeople));
    }

    @Test
    public void testCreateReservationAlreadyExists() {
        // Arrange
        Long customerId = 1L;
        Long restaurantId = 2L;
        String dateTimeString = "2023-10-10T12:00:00";
        int numberOfPeople = 4;

        Customer customer = new Customer();
        customer.setId(customerId);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));
        when(reservationRepository.existsByReservationDateTimeAndRestaurant(
                LocalDateTime.parse(dateTimeString), restaurant))
                .thenReturn(true);

        // Act & Assert
        assertThrows(ResourceAlreadyExistsException.class,
                () -> reservationService.createReservation(customerId, restaurantId, dateTimeString, numberOfPeople));
    }
}
