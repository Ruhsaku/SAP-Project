package com.example.demo.Customer;

import com.example.demo.Employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository
        extends JpaRepository<Customer, Long> {
    // SELECT * FROM student WHERE email = ?;
    @Query("SELECT c FROM Customer c WHERE c.email = ?1")
    Optional<Customer> findCustomerByEmail(String email);

    // SELECT * FROM student WHERE email = ? AND password = ?;
    @Query("SELECT c FROM Customer c WHERE c.email = ?1 AND c.password = ?2")
    Optional<Customer> findCustomerByEmailAndPassword(String email, String password);
}
