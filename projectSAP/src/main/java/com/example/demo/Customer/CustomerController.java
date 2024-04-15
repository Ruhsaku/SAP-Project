package com.example.demo.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
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

    //@PostMapping("/register")
    //@ResponseBody
    public String registerNewCustomer(@RequestBody Customer customer) {
        try {
            customerService.addNewCustomer(customer);
            return "Registration successful. Please login.";
        } catch (IllegalStateException e) {
            return "Registration failed. Please try again.";
        }
    }

    //@PostMapping("/")
    // @ResponseBody
    public String loginCustomer(@RequestBody Customer customer) {
        try {
            boolean loginSuccessful = customerService.login(customer);
            if (loginSuccessful) {
                return "Login successful.";
            } else {
                return "Invalid email or password. Please try again.";
            }
            // return "redirect:/home";
        } catch (IllegalStateException e) {
            return "Login failed. Please try again.";
            // return "redirect:/?error";
        }
    }
}
