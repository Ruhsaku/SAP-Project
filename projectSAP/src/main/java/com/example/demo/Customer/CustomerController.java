package com.example.demo.Customer;

import com.example.demo.Employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping("/register")
    public String registerNewCustomer(@RequestBody Customer customer) {
        try {
            customerService.addNewCustomer(customer);
            return "redirect:/";
        } catch (IllegalStateException e) {
            return "redirect:/?error";
        }
    }

    @PostMapping("/index")
    public String loginCustomer(@RequestBody Customer customer) {
        try {
            customerService.login(customer);
            return "redirect:/home";
        } catch (IllegalStateException e) {
            return "redirect:/?error";
        }
    }
}
