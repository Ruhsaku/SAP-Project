package com.example.demo.Web;

import com.example.demo.Customer.Customer;
import com.example.demo.Customer.CustomerRepository;
import com.example.demo.Employee.EmployeeRepository;
import com.example.demo.Regexes.RegisterRegexes;
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
        if (!RegisterRegexes.validateUsername(username)) {
            return "register";
        }

        // Validate email
        if (!RegisterRegexes.validateEmployeeEmail(email)) {
            return "register";
        }
        //Validate password1
        if(!RegisterRegexes.validPassword(password)){
            return "register";
        }
        // Validate password2
        if (!Character.isUpperCase(password.charAt(0))) {
            return "register";
        }


//        if (!containsNumber(password)) {
//            return "register";
//        }



        // If all validations pass, determine the role based on the email
        if (RegisterRegexes.validateEmployeeEmail(email)) {
            return "Employee registered successfully.";
        } else {
            Customer customer = new Customer(firstName, lastName,username, email, password);
            customerRepository.save(customer);
            return "/";
        }
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
