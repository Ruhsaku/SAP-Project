package com.example.demo.Employee;

import com.example.demo.PasswordHash.PasswordEncoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeConfig {
    @Bean
    CommandLineRunner commandLineRunner2(EmployeeRepository repository) {

        return args -> {
            Employee martin = new Employee(
                    "Martin",
                    "Yovchev",
                    "MartoMan",
                    PasswordEncoder.encodePassword("Kivim543"),
                    "mayovchev@per-store.com",
                    55000.50,
                    "0897654321",
                    "developer"
            );

            repository.save(martin);
        };
    }
}
