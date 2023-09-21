package com.pe.hampcode.reservation.domain.service;


import com.pe.hampcode.customer.domain.entity.Customer;
import com.pe.hampcode.reservation.domain.entity.Reservation;
import com.pe.hampcode.reservation.resource.ReservationResource;
import com.pe.hampcode.reservation.resource.ReservationResponseResource;
import com.pe.hampcode.restaurant.domain.entity.Restaurant;
import com.pe.hampcode.customer.domain.persistence.CustomerRepository;
import com.pe.hampcode.reservation.domain.persistence.ReservationRepository;
import com.pe.hampcode.restaurant.domain.persistence.RestaurantRepository;
import com.pe.hampcode.reservation.mappers.ReservationMapper;
import com.pe.hampcode.shared.exception.ResourceAlreadyExistsException;
import com.pe.hampcode.shared.exception.ResourceNotFoundException;
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
    public ReservationResponseResource createReservation(
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


        return reservationMapper.entityToResponseResource(reservation);//reservationRepository.save(reservation);
    }


}
