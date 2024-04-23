package com.racooncoding.perfumestore.customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository
        extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByEmail(String email);
    Optional<Customer> findCustomerByEmailAndPassword(String email, String password);
}
