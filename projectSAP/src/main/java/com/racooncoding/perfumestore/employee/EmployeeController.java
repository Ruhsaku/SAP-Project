package com.racooncoding.perfumestore.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(path ="/login/employee")
    @ResponseBody
    public ResponseEntity<?> loginEmployee(@RequestBody Employee employee) {
        // TODO --> Exception Handling
        try {
            boolean loginSuccessful = employeeService.login(employee);
            if (loginSuccessful) {
                System.out.println("Login successful. Let's work!");
                return ResponseEntity.ok().body("{\"redirectUrl\": \"/dashboard\"}");
            } else {
                System.err.println("Invalid email or password. Please try again.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("{\"error\": \"Login failed\"}");
            }
        } catch (IllegalStateException e) {
            System.err.println("Login failed. Please try again.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"error\": \"Login failed\"}");
        }
    }
}
