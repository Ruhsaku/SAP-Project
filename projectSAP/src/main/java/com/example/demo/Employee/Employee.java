package com.example.demo.Employee;

import com.example.demo.UserType;
import com.example.demo.User;

public class Employee extends User {
    private Long employeeId;
    private Float salary;
    private String contactNumber;
    private String position;

    public Employee() {

    }

    public Employee(Long employeeId,
                    Float salary,
                    String contactNumber,
                    String position) {
        this.employeeId = employeeId;
        this.salary = salary;
        this.contactNumber = contactNumber;
        this.position = position;
    }

    public Employee(String password,
                    String username,
                    Long employeeId,
                    Float salary,
                    String contactNumber,
                    String position) {
        super(password, username);
        this.employeeId = employeeId;
        this.salary = salary;
        this.contactNumber = contactNumber;
        this.position = position;
    }

    public Employee(Long userId,
                    String firstName,
                    String lastName,
                    String password,
                    String username,
                    String email,
                    Long employeeId,
                    Float salary,
                    String contactNumber,
                    String position) {
        super(userId, firstName, lastName, password, username, email);
        this.employeeId = employeeId;
        this.salary = salary;
        this.contactNumber = contactNumber;
        this.position = position;
    }

    public Employee(String firstName,
                    String lastName,
                    String password,
                    String username,
                    String email,
                    Long employeeId,
                    Float salary,
                    String contactNumber,
                    String position) {
        super(firstName, lastName, password, username, email);
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
