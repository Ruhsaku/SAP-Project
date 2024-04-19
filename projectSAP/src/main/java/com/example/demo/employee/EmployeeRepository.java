package com.example.demo.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {

    // @Query("SELECT e FROM Employee e WHERE e.email = ?1 and e.password = ?2")
    Optional<Employee> findEmployeeByEmailAndPassword(String email, String password);
}
