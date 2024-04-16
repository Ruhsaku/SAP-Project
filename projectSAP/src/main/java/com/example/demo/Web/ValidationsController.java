package com.example.demo.web;

import com.example.demo.customer.Customer;
import com.example.demo.customer.CustomerRepository;
import com.example.demo.employee.EmployeeRepository;
import com.example.demo.regexes.RegisterRegexes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ValidationsController {
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;

    // TODO: Move to CustomerController
    @PostMapping("/register/customer")
    public String registerCustomer(@RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password) {
        // Validate username
        if (!RegisterRegexes.validateUsername(username)) {
            System.err.println("Error validating the username...");
            return "redirect:/register";
        }

        // Validate email
        if (!RegisterRegexes.validateEmployeeEmail(email)) {
            System.err.println("Error validating the email...");
            return "redirect:/register";
        }
        //Validate password1
        if(!RegisterRegexes.validPassword(password)){
            System.err.println("Error validating the password...");
            return "redirect:/register";
        }
        // Validate password2
//        if (!Character.isUpperCase(password.charAt(0))) {
//            System.err.println("Error validating the email...");
//            return "redirect:/register";
//        }


//        if (!containsNumber(password)) {
//            return "register";
//        }



        // If all validations pass, determine the role based on the email
        Customer customer = new Customer(firstName, lastName,username, email, password);
        customerRepository.save(customer);
        return "redirect:/";

    }

//    private boolean containsNumber(String password) {
//        for (char c : password.toCharArray()) {
//            if (Character.isDigit(c)) {
//                return true;
//            }
//        }
//        return false;
//    }

//    private boolean isEmployee(String email) {
//        return email.endsWith("@per-store.com");
//    }
}
