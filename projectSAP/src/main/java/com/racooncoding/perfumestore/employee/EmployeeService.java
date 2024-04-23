package com.racooncoding.perfumestore.employee;

import com.racooncoding.perfumestore.passwordhash.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public void saveEmployees(List<Employee> employees) {
        for (Employee emp: employees) {
            String hashedPassword = PasswordEncoder.encodePassword(emp.getPassword());
            emp.setPassword(hashedPassword);
        }
        employeeRepository.saveAll(employees);
    }

    public boolean login(Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository
                .findEmployeeByEmailAndPassword(
                        employee.getEmail(), employee.getPassword());
        return employeeOptional.isPresent();
    }
}