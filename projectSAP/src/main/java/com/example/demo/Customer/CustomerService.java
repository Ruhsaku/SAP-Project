package com.example.demo.Customer;

import com.example.demo.Employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
//    private final CustomerRepository customerRepository;
//
//    @Autowired
//    public CustomerService (CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }

    private final Map<Long, Customer> customerMap = new HashMap<>();
    private static Long nextCustomerId = 1L;

    public Customer createCustomer(Customer customer) {
        customer.setCustomerId(nextCustomerId++);
        customerMap.put(customer.getCustomerId(), customer);
        return customer;
    }

    public Customer getCustomerById(Long customerId) {
        return customerMap.get(customerId);
    }

//    public List<Customer> getCustomers() {
//        return customerRepository.findAll();
//    }
}
