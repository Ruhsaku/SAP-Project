package com.racooncoding.perfumestore.customer;

import com.racooncoding.perfumestore.exceptions.InvalidCredentialsException;
import com.racooncoding.perfumestore.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping(path = "/register")
    public ResponseEntity<Response> registerNewCustomer(@RequestBody Customer customer) {
        Response response;
        try {
            customerService.addNewCustomer(customer);
            response = new Response("Registration successful. Please login.", "/login");
            System.out.println(response.getMessage());
            return ResponseEntity.ok().body(response);
        } catch (InvalidCredentialsException e) {
            response = new Response("Registration failed. There is invalid data. Please try again.");
            System.err.println(response.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(response);
        }
    }

    @PostMapping(path = "/login")
    @ResponseBody
    public ResponseEntity<Response> loginCustomer(@RequestBody Customer customer) {
        Response response;
        boolean loginSuccessful = customerService.login(customer);
        if (loginSuccessful) {
            response = new Response("Login successful. Have fun!", "/home");
            System.out.println(response.getMessage());
            return ResponseEntity.ok().body(response);
        } else {
            throw new InvalidCredentialsException();
        }
    }
}
