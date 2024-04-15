package com.example.demo.Employee;

import com.example.demo.Customer.Customer;
import com.example.demo.PasswordHash.PasswordDecoder;
import com.example.demo.PasswordHash.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

    public void addNewEmployee(Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository
                .findEmployeeByEmail(employee.getEmail());

        if (employeeOptional.isPresent()) {
            throw new IllegalStateException("This email is taken");
        }
        employeeRepository.save(employee);
    }

    public boolean login(Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository
                .findEmployeeByEmail(employee.getEmail());
        if (employeeOptional.isPresent()) {
            Employee existingEmployee = employeeOptional.get();
            String hashedPassword = PasswordEncoder.encodePassword(employee.getPassword());
            return PasswordDecoder.verifyPassword(existingEmployee.getPassword(), hashedPassword);
        } else {
            throw new IllegalStateException("User not found");
        }
    }
}
