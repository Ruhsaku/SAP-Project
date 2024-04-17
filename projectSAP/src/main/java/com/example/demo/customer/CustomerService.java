package com.example.demo.customer;

import com.example.demo.passwordhash.PasswordDecoder;
import com.example.demo.passwordhash.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @ResponseBody
    public void addNewCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository
                .findCustomerByEmail(customer.getEmail());

        if (customerOptional.isPresent()) {
            throw new IllegalStateException("This email is taken");
        } else {
            customer.setPassword(PasswordEncoder.encodePassword(customer.getPassword()));
            customerRepository.save(customer);
        }
    }

    public boolean login(Customer customer) {
        Optional<Customer> customerOptional = customerRepository
                .findCustomerByEmail(customer.getEmail());
        if (customerOptional.isPresent()) {
            Customer existingCustomer = customerOptional.get();
            String hashedPassword = PasswordEncoder.encodePassword(customer.getPassword());
            return PasswordDecoder.verifyPassword(existingCustomer.getPassword(), hashedPassword);
        } else {
            throw new IllegalStateException("User not found");
        }
    }
}
