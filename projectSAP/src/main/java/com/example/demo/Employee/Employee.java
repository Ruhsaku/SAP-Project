package com.example.demo.Employee;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private Double salary;
    private String contactNumber;
    private String position;

    public Employee() {

    }

    public Employee(String firstName,
                    String lastName,
                    String username,
                    String password,
                    String email,
                    Double salary,
                    String contactNumber,
                    String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.salary = salary;
        this.contactNumber = contactNumber;
        this.position = position;
    }
    public Employee(Integer employeeId,
                    String firstName,
                    String lastName,
                    String username,
                    String password,
                    String email,
                    Double salary,
                    String contactNumber,
                    String position) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.salary = salary;
        this.contactNumber = contactNumber;
        this.position = position;
    }


    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(username, employee.username) && Objects.equals(password, employee.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", contactNumber='" + contactNumber + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
