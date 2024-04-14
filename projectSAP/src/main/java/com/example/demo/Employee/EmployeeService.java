package com.example.demo.Employee;

import com.example.demo.PasswordEncoder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Transactional
public class EmployeeService extends PasswordEncoder {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // private final Map<Long, Employee> employeeMap = new HashMap<>();
    // private static Long nextEmployeeId = 1L;

    public Employee createEmployee(Employee employee) {
        // employee.setEmployeeId(nextEmployeeId++);
        // employeeMap.put(employee.getEmployeeId(), employee);
        return employeeRepository.save(employee);
    }
    public void saveEmployee(Employee employee) {
        employee.setPassword(PasswordEncoder.encodePassword(employee.getPassword()));
        employeeRepository.save(employee);
    }

    public void saveEmployees(List<Employee> employees) {
        employeeRepository.saveAll(employees);
    }

//    public Employee getEmployeeByUsername(String username) {
//        return employeeRepository.findByUsername(username);
//    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }
}
