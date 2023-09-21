package com.pe.hampcode.reservation.domain.persistence;

import com.pe.hampcode.customer.domain.entity.Customer;
import com.pe.hampcode.reservation.domain.entity.Reservation;
import com.pe.hampcode.restaurant.domain.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByReservationDateTimeAndRestaurant(LocalDateTime reservationDateTime, Restaurant restaurant);
    List<Reservation> findByCustomer(Customer customer);
}
