package com.example.demo.Customer;

import com.example.demo.User;
import com.example.demo.UserType;

public class Customer extends User {
    public Customer(Long id, String password, String username) {
        super(id, password, username);
    }

    @Override
    public UserType getUserType() {
        return UserType.CUSTOMER;

    }
}
