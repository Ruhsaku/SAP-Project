package com.racooncoding.perfumestore.employee;

import com.racooncoding.perfumestore.exceptions.InvalidCredentialsException;
import com.racooncoding.perfumestore.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(path = "/login/employee")
    @ResponseBody
    public ResponseEntity<Response> loginEmployee(@RequestBody Employee employee) {
        Response response;
        boolean loginSuccessful = employeeService.login(employee);
        if (loginSuccessful) {
            response = new Response("Login successful. Let's work!", "/dashboard");
            System.out.println(response.getMessage());
            return ResponseEntity.ok().body(response);
        } else {
            throw new InvalidCredentialsException();
        }
    }
}
