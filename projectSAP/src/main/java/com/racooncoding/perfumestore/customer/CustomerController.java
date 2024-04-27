
package com.racooncoding.perfumestore.customer;

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

    // TODO ResponseEntity<Response> instead of "?"
    // Try to avoid using the wildcard template parameter "?".
    // In this case, I understand why it's used but generally try to void returning 2 different types in one method.
    @PostMapping("/register")
    public ResponseEntity<?> registerNewCustomer(@RequestBody Customer customer) {
        try {
            customerService.addNewCustomer(customer);
            System.out.println("Registration successful. Please login.");
            return ResponseEntity.ok().body("{\"redirectUrl\": \"/login\"}");
        } catch (IllegalStateException e) {
            System.err.println("Registration failed. Please try again.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Registration failed\"}");
        }
    }

    @PostMapping(path = "/login")
    @ResponseBody
    public ResponseEntity<?> loginCustomer(@RequestBody Customer customer) {
        try {
            boolean loginSuccessful = customerService.login(customer);
            if (loginSuccessful) {
                System.out.println("Login successful. Have fun!");
                return ResponseEntity.ok().body("{\"redirectUrl\": \"/home\"}");
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
