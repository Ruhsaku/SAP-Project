package com.example.demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //@GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping("/register/customer")
    @ResponseBody
    public String registerNewCustomer(@RequestBody Customer customer) {
        try {
            customerService.addNewCustomer(customer);
            System.out.println("Registration successful. Please login.");
            return "redirect:/login";
        } catch (IllegalStateException e) {
            System.err.println("Registration failed. Please try again.");
            return "redirect:/register/customer";
        }
    }


    @PostMapping(value = "/login/customer")
    @ResponseBody
    public String loginCustomer(@RequestBody Customer customer) {
        try {
            boolean loginSuccessful = customerService.login(customer);
            if (loginSuccessful) {
                System.out.println("Login successful.");
                return "redirect:/home";
            } else {
                System.err.println("Invalid email or password. Please try again.");
                return "redirect:/login/customer";
            }
        } catch (IllegalStateException e) {
            System.err.println("Login failed. Please try again.");
            return "redirect:/login/customer";
        }
    }
}
