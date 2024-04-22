package com.example.demo.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.passwordhash.PasswordEncoder;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService (CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    // A @ResponseBody on a void method? Why?
    @ResponseBody
    public void addNewCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository
                .findCustomerByEmail(customer.getEmail());

        if (customerOptional.isPresent()) {
            // A customer entity present with the same email is not an invalid state of the server/db.
            // Use a different exception.
            throw new IllegalStateException("This email is taken");
        } else {
            customer.setPassword(PasswordEncoder.encodePassword(customer.getPassword()));
            customerRepository.save(customer);
        }
    }

    public boolean login(Customer customer) {
        // A password should not be a distinguishing property when querying for an entity
        Optional<Customer> customerOptional = customerRepository
                .findCustomerByEmailAndPassword(customer.getEmail(),
                        PasswordEncoder.encodePassword(customer.getPassword()));
        return customerOptional.isPresent();
    }
}
