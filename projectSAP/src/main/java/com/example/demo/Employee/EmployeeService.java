package com.example.demo.Employee;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {

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

}
