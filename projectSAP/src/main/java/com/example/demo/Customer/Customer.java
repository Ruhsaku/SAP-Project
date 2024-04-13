package com.example.demo.Customer;

import com.example.demo.User;
import com.example.demo.UserType;
import jakarta.persistence.*;

@Entity
@Table
public class Customer extends User {
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    private Long customerId;
    private Integer loyaltyPoints;

    public Customer() {

    }

    public Customer(Long customerId,
                    String firstName,
                    String lastName,
                    String password,
                    String username,
                    String email,
                    Integer loyaltyPoints) {
        super(firstName, lastName, password, username, email);
        this.customerId = customerId;
        this.loyaltyPoints = loyaltyPoints;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(Integer loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public UserType getUserType() {
        return UserType.CUSTOMER;
    }
}
