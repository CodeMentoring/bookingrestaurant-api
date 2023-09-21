package com.pe.hampcode.customer.domain.persistence;

import com.pe.hampcode.customer.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByFirstName(String firstName);

    boolean existsByEmail(String email);

    Optional<Customer> findByEmail(String email);
}