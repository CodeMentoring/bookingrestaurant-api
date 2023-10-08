package com.pe.hampcode.service;


import com.pe.hampcode.dto.ReservationResponseDto;
import com.pe.hampcode.entity.Customer;
import com.pe.hampcode.exception.ResourceAlreadyExistsException;
import com.pe.hampcode.exception.ResourceNotFoundException;
import com.pe.hampcode.mappers.ReservationMapper;
import com.pe.hampcode.repository.CustomerRepository;
import com.pe.hampcode.repository.ReservationRepository;
import com.pe.hampcode.entity.Reservation;
import com.pe.hampcode.entity.Restaurant;
import com.pe.hampcode.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final ReservationMapper reservationMapper;

    @Transactional
    public ReservationResponseDto createReservation(
            Long customerId,
            Long restaurantId,
            //LocalDateTime reservationDateTime,
            String dateTimeString,
            int numberOfPeople
    ) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + customerId));

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurante no encontrado con ID: " + restaurantId));


        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime reservationDateTime = LocalDateTime.parse(dateTimeString, formatter);

        if (reservationRepository.existsByReservationDateTimeAndRestaurant(reservationDateTime, restaurant)) {
            throw new ResourceAlreadyExistsException("Ya existe una reserva para esta fecha y hora en este restaurante.");
        }


        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setRestaurant(restaurant);
        reservation.setReservationDateTime(reservationDateTime);
        //reservation.setReservationDateTime(LocalDateTime.now());
        reservation.setNumberOfPeople(numberOfPeople);

        reservationRepository.save(reservation);

        return reservationMapper.entityToResponseResource(reservation);
    }


}
