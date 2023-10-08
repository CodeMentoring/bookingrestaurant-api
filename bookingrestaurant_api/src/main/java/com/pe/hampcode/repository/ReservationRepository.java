package com.pe.hampcode.repository;

import com.pe.hampcode.entity.Customer;
import com.pe.hampcode.entity.Reservation;
import com.pe.hampcode.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByReservationDateTimeAndRestaurant(LocalDateTime reservationDateTime, Restaurant restaurant);
    List<Reservation> findByCustomer(Customer customer);
}
