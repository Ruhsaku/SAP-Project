package com.example.demo.Employee;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EmployeeConfig {
    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository repository) {
        return args -> {
            Employee martin = new Employee(
                    1L,
                    "Martin",
                    "Yovchev",
                    "azSumGei",
                    "Gei",
                    "mayovchev@per-store.com",
                    55000.50F,
                    "0897654321",
                    "developer"
            );

            Employee stanislav = new Employee(
                    2L,
                    "Stanislav",
                    "Mitev",
                    "azSumPi4",
                    "Pi4aga",
                    "stmitev@per-store.com",
                    55000.50F,
                    "0891234567",
                    "sys admin"
            );

            repository.saveAll(
                    List.of(martin, stanislav)
            );
        };
    }
}