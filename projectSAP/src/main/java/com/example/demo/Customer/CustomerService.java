package com.example.demo.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService (CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

//     private final Map<Long, Customer> customerMap = new HashMap<>();
//     private static Long nextCustomerId = 1L;

    public Customer createCustomer(Customer customer) {
        // customer.setCustomerId(nextCustomerId++);
        // customerMap.put(customer.getCustomerId(), customer);
        return customerRepository.save(customer);
    }

//    public Customer getCustomerById(Long customerId) {
//        return customerMap.get(customerId);
//    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}
