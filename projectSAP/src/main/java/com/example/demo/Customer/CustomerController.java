package com.example.demo.Customer;

import com.example.demo.Employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

//    @GetMapping("/{customerId}")
//    public Customer getCustomerById(@PathVariable("customerId") Long customerId) {
//        return customerService.getCustomerById(customerId);
//    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

}
