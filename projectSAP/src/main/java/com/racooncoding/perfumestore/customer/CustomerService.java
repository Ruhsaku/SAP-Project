package com.racooncoding.perfumestore.customer;

import com.racooncoding.perfumestore.passwordhash.PasswordDecoder;
import com.racooncoding.perfumestore.passwordhash.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addNewCustomer(Customer customer) {
        // TODO --> Fix the Business logic
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());

        if (customerOptional.isPresent()) {
            throw new IllegalArgumentException("Email address is already in use.");
        } else {
            customer.setPassword(PasswordEncoder.encodePassword(customer.getPassword()));
            customerRepository.save(customer);
        }
    }

    public boolean login(Customer customer) {
        // TODO --> Fix the Business logic
        Optional<Customer> customerOptional = customerRepository.
                findCustomerByEmail(customer.getEmail());

        if (customerOptional.isPresent()) {
            Customer storedCustomer = customerOptional.get();
            if (PasswordDecoder.verifyPassword(customer.getPassword(), storedCustomer.getPassword())) {
                return true; // Login successful
            } else {
                throw new IllegalArgumentException("Incorrect password.");
            }
        } else {
            throw new IllegalArgumentException("Customer with the given email address does not exist.");
        }
    }
}
