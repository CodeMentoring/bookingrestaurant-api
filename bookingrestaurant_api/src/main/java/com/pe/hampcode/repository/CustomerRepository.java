package com.pe.hampcode.repository;

import com.pe.hampcode.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByFirstName(String firstName);

    boolean existsByEmail(String email);

    Optional<Customer> findByEmail(String email);
}