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
            generator = "student_sequence"
    )
    private Long customerId;
    private Integer loyaltyPoints;

    public Customer() {

    }

    public Customer(Long customerId,
                    Integer loyaltyPoints) {
        this.customerId = customerId;
        this.loyaltyPoints = loyaltyPoints;
    }

    public Customer(String password,
                    String username,
                    Long customerId,
                    Integer loyaltyPoints) {
        super(password, username);
        this.customerId = customerId;
        this.loyaltyPoints = loyaltyPoints;
    }

    public Customer(Long userId,
                    String firstName,
                    String lastName,
                    String password,
                    String username,
                    String email,
                    Long customerId,
                    Integer loyaltyPoints) {
        super(userId, firstName, lastName, password, username, email);
        this.customerId = customerId;
        this.loyaltyPoints = loyaltyPoints;
    }

    public Customer(String firstName,
                    String lastName,
                    String password,
                    String username,
                    String email,
                    Long customerId,
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
