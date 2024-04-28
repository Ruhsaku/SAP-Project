package com.racooncoding.perfumestore.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public boolean login(Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository
                .findEmployeeByEmailAndPassword(
                        employee.getEmail(), employee.getPassword());
        return employeeOptional.isPresent();
    }
}
