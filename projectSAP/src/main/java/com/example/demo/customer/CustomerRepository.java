package com.example.demo.customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository
        extends JpaRepository<Customer, Long> {

    // Why are these comments left here?
    // @Query("SELECT c FROM Customer c WHERE c.email = ?1")
    Optional<Customer> findCustomerByEmail(String email);

    // @Query("SELECT c FROM Customer c WHERE c.email = ?1 AND c.password = ?2")
    Optional<Customer> findCustomerByEmailAndPassword(String email, String password);
}
