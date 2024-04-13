package com.example.demo.Employee;

import com.example.demo.Customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
//    private final EmployeeRepository employeeRepository;
//
//    @Autowired
//    public EmployeeService(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }

    private final Map<Long, Employee> employeeMap = new HashMap<>();
    private static Long nextEmployeeId = 1L;

    public Employee createEmployee(Employee employee) {
        employee.setEmployeeId(nextEmployeeId++);
        employeeMap.put(employee.getEmployeeId(), employee);
        return employee;
    }

    public Employee getEmployeeById(Long employeeId) {
        return employeeMap.get(employeeId);
    }

//    public List<Employee> getEmployees() {
//        return employeeRepository.findAll();
//    }
}
