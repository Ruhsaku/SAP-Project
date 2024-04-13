package com.example.demo.Customer;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerService {

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

}
