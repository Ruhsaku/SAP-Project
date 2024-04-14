package com.example.demo.Employee;

import com.example.demo.PasswordHash.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {
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
        employeeRepository.save(employee);
    }

    public void saveEmployees(List<Employee> employees) {
        for (Employee emp: employees) {
            String hashedPassword = PasswordEncoder.encodePassword(emp.getPassword());
            emp.setPassword(hashedPassword);
        }
        employeeRepository.saveAll(employees);
    }

//    public Employee getEmployeeByUsername(String username) {
//        return employeeRepository.findByUsername(username);
//    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }
}
