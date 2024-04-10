package com.example.demo.Employee;

import com.example.demo.UserType;
import com.example.demo.User;

public class Employee extends User {
    public Employee(Long id, String password, String username) {
        super(id, password, username);
    }

    @Override
    public UserType getUserType() {
        return UserType.EMPLOYEE;

    }
}
