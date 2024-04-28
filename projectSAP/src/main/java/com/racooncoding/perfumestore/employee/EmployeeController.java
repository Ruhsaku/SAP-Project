package com.racooncoding.perfumestore.employee;

import com.racooncoding.perfumestore.response.Response;
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
    public ResponseEntity<Response> loginEmployee(@RequestBody Employee employee) {
        // TODO --> Exception Handling
        Response response;
        try {
            boolean loginSuccessful = employeeService.login(employee);
            if (loginSuccessful) {
                response = new Response("Login successful. Let's work!", "/dashboard");
                System.out.println(response.getMessage());
                return ResponseEntity.ok().body(response);
            } else {
                response = new Response("Employee not found!");
                System.err.println(response.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(response);
            }
        } catch (IllegalStateException e) {
            response = new Response("Login failed.");
            System.err.println(response.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }
}
