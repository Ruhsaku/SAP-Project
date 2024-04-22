package com.example.demo.customer;

import java.util.List;

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

    // Unused method.
    //@GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping("/register")
    // It is redundant to add @ResponseBody to methods of a @RestController class. Remove them
    @ResponseBody
    // Try to avoid using the wildcard template parameter "?".
    // In this case, I understand why it's used but generally try to void returning 2 different types in one method.
    public ResponseEntity<?> registerNewCustomer(@RequestBody Customer customer) {
        try {
            customerService.addNewCustomer(customer);
            System.out.println("Registration successful. Please login.");
            return ResponseEntity.ok().body("{\"redirectUrl\": \"/\"}");
        } catch (IllegalStateException e) {
            System.err.println("Registration failed. Please try again.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Registration failed\"}");
        }
    }

    // POST / is for login? I don't like this API.
    // Why not POST /login?
    @PostMapping(value = "/")
    @ResponseBody
    public ResponseEntity<?> loginCustomer(@RequestBody Customer customer) {
        try {
            boolean loginSuccessful = customerService.login(customer);
            if (loginSuccessful) {
                System.out.println("Login successful. Have fun!");
                return ResponseEntity.ok().body("{\"redirectUrl\": \"/home\"}");
            }
            else {
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
