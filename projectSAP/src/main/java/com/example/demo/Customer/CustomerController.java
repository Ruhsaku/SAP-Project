package com.example.demo.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") Long customerId) {
        return customerService.getCustomerById(customerId);
    }

}
