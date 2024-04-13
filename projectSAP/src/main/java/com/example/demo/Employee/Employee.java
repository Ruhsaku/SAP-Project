package com.example.demo.Employee;

import com.example.demo.UserType;
import com.example.demo.User;
import jakarta.persistence.*;

@Entity
@Table
public class Employee extends User {
    @Id
    @SequenceGenerator(
            name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    private Long employeeId;
    private Float salary;
    private String contactNumber;
    private String position;

    public Employee() {

    }


    public Employee(Long employeeId,
                    String firstName,
                    String lastName,
                    String username,
                    String password,
                    String email,
                    Float salary,
                    String contactNumber,
                    String position) {
        super(firstName, lastName, username, password, email);
        this.employeeId = employeeId;
        this.salary = salary;
        this.contactNumber = contactNumber;
        this.position = position;
    }


    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public UserType getUserType() {
        return UserType.EMPLOYEE;
    }
}
