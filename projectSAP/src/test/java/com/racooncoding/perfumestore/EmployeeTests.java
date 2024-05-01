package com.racooncoding.perfumestore;

import com.racooncoding.perfumestore.employee.Employee;
import com.racooncoding.perfumestore.employee.EmployeeRepository;
import com.racooncoding.perfumestore.employee.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void testLogin_Successful() {
        // Mock data
        Employee employee = new Employee("John", "Doe", "johndoe", "password".toCharArray(), "john@example.com", null, null, null);
        when(employeeRepository.findEmployeeByEmailAndPassword("john@example.com", "password")).thenReturn(Optional.of(employee));

        assertTrue(employeeService.login(employee));
    }

    @Test
    void testLogin_Unsuccessful() {
        // Arrange
        Employee employee = new Employee("John", "Doe", "johndoe", "password".toCharArray(), "john@example.com", null, null, null);
        when(employeeRepository.findEmployeeByEmailAndPassword(employee.getEmail(), employee.getPassword())).thenReturn(Optional.empty());

        assertFalse(employeeService.login(employee));
    }
}

