package com.example.demo.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    @PostMapping
//    public Employee createEmployee(@RequestBody Employee employee) {
//
//        return employeeService.createEmployee(employee);
//    }

//    @GetMapping("/{employeeId}")
//    public Employee getEmployeeById(@PathVariable("employeeId") Long employeeId) {
//        return employeeService.getEmployeeById(employeeId);
//    }

//    @GetMapping
//    public List<Employee> getEmployees() {
//        return employeeService.getEmployees();
//    }
}
