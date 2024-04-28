package com.racooncoding.perfumestore.customer;

import com.racooncoding.perfumestore.exceptions.EmailAlreadyUsedException;
import com.racooncoding.perfumestore.exceptions.IncorrectPasswordException;
import com.racooncoding.perfumestore.exceptions.UserNotFoundException;
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
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());

        if (customerOptional.isPresent()) {
            throw new EmailAlreadyUsedException();
        } else {
            customer.setPassword(PasswordEncoder.encodePassword(customer.getPassword()));
            customerRepository.save(customer);
        }
    }

    public boolean login(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.
                findCustomerByEmail(customer.getEmail());

        if (customerOptional.isPresent()) {
            Customer storedCustomer = customerOptional.get();
            if (PasswordDecoder.verifyPassword(customer.getPassword(), storedCustomer.getPassword())) {
                return true;
            } else {
                throw new IncorrectPasswordException();
            }
        } else {
            throw new UserNotFoundException();
        }
    }
}
