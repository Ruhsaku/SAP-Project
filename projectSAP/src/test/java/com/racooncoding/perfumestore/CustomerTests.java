package com.racooncoding.perfumestore;

import com.racooncoding.perfumestore.customer.*;
import com.racooncoding.perfumestore.exceptions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerTests {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void testAddNewCustomer_Successful() {
        Customer customer = new Customer("John", "Doe", "password".toCharArray(), "johndoe", "john@example.com");
        when(customerRepository.findCustomerByEmail(customer.getEmail())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> customerService.addNewCustomer(customer));
    }

    @Test
    void testAddNewCustomer_EmailAlreadyUsed() {
        Customer customer = new Customer("John", "Doe", "password".toCharArray(), "johndoe", "john@example.com");
        when(customerRepository.findCustomerByEmail(customer.getEmail())).thenReturn(Optional.of(customer));

        assertThrows(EmailAlreadyUsedException.class, () -> customerService.addNewCustomer(customer));
    }

    @Test
    void testLoginCustomer_Successful() {
        Customer customer = new Customer("John", "Doe", "password".toCharArray(), "johndoe", "john@example.com");
        // Mock the customer repository
        when(customerRepository.findCustomerByEmail(customer.getEmail())).thenReturn(Optional.of(customer));

        assertTrue(customerService.login(customer));
    }

    @Test
    void testLoginCustomer_Unsuccessful() {
        Customer customer = new Customer("John", "Doe", "password".toCharArray(), "johndoe", "john@example.com");
        // Mocking the behavior to return a non-empty Optional with the customer object
        when(customerRepository.findCustomerByEmail(customer.getEmail())).thenReturn(Optional.of(customer));

        assertFalse(customerService.login(new Customer("John", "Doe", "wrongpassword".toCharArray(), "johndoe", "john@example.com")));
    }

}
