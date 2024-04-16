package com.example.demo.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //@GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    //@PostMapping("/saveAll")
    public void saveEmployees(@RequestBody List<Employee> employees) {
        employeeService.saveEmployees(employees);
    }

    @PostMapping(path = "/register/employee",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerNewEmployee(@RequestBody Employee employee) {
        employeeService.addNewEmployee(employee);
    }

    @PostMapping("/")
    @ResponseBody
    public String loginEmployee(@RequestBody Employee employee) {
        try {
            boolean loginSuccessful = employeeService.login(employee);
            if (loginSuccessful) {
                // return "Login successful.";
                return "home";
            } else {
                // return "Invalid email or password. Please try again.";
                return "Invalid email";
            }
            // return "redirect:/home";
        } catch (IllegalStateException e) {
            // return "Login failed. Please try again.";
            return "redirect:/?error";
        }
    }
}
