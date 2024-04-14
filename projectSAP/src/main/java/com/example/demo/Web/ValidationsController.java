package com.example.demo.Web;

import com.example.demo.Customer.Customer;
import com.example.demo.Customer.CustomerRepository;
import com.example.demo.Employee.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.regex.Pattern;

@Controller
public class ValidationsController {

    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;

    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password) {
        // Validate username
        if (username.length() < 3) {
            return "register";
        }

        // Validate email
        if (!email.endsWith("@per-store.com")) {
            return "register";
        }

        // Validate password
        if (!Character.isUpperCase(password.charAt(0))) {
            return "register";
        }

        if (!containsNumber(password)) {
            return "register";
        }

        if (!Pattern.matches("[a-zA-Z0-9]+", password)) {
            return "register";
        }

        // If all validations pass, determine the role based on the email
        if (isEmployee(email)) {
            return "Employee registered successfully.";
        } else {
            Customer customer = new Customer(firstName, lastName,username, email, password);
            customerRepository.save(customer);
            return "/";
        }
    }

    private boolean containsNumber(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean isEmployee(String email) {
        return email.endsWith("@per-store.com");
    }
}
