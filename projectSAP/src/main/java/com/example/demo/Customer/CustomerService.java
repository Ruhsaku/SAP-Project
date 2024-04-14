package com.example.demo.Customer;

import com.example.demo.Employee.Employee;
import com.example.demo.PasswordHash.PasswordDecoder;
import com.example.demo.PasswordHash.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


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
            throw new IllegalStateException("This email is taken");
        } else {
            customer.setPassword(PasswordEncoder.encodePassword(customer.getPassword()));
        }
        customer.setFirstName(customer.getFirstName());
        customer.setLastName(customer.getLastName());
        customer.setEmail(customer.getEmail());
        customer.setUsername(customer.getUsername());
        customerRepository.save(customer);
    }

    public void login(Customer customer) {
        Optional<Customer> customerOptional = customerRepository
                .findCustomerByEmail(customer.getEmail());
        if (customerOptional.isPresent()) {
            Customer existingCustomer = customerOptional.get();
            String hashedPassword = PasswordEncoder.encodePassword(customer.getPassword());
            if(!PasswordDecoder.verifyPassword(existingCustomer.getPassword(), hashedPassword)) {
                throw new IllegalStateException("Wrong email or password");
            }
        } else {
            throw new IllegalStateException("User not found");
        }
    }
}
