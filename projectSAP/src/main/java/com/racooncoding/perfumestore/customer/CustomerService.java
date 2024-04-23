package com.racooncoding.perfumestore.customer;

import java.util.List;
import java.util.Optional;

import com.racooncoding.perfumestore.passwordhash.PasswordDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.racooncoding.perfumestore.passwordhash.PasswordEncoder;

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

    public void addNewCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository
                .findCustomerByEmail(customer.getEmail());

        if (customerOptional.isPresent()) {
            // TODO email validation
            // A customer entity present with the same email is not an invalid state of the server/db.
            // Use a different exception.
            throw new IllegalStateException("This email is taken");
        } else {
            customer.setPassword(PasswordEncoder.encodePassword(customer.getPassword()));
            customerRepository.save(customer);
        }
    }

    public boolean login(Customer customer) {
        // Retrieve the customer by email
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());

        if (customerOptional.isPresent()) {
            Customer storedCustomer = customerOptional.get();
            // Compare the hashed password with the provided password
            return PasswordDecoder.verifyPassword(
                    customer.getPassword(), storedCustomer.getPassword());
        }

        return false; // Customer not found
    }
}
