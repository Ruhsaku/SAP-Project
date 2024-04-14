//package com.example.demo.Customer;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class CustomerConfig {
//    @Bean
//    CommandLineRunner commandLineRunner(CustomerRepository repository) {
//
//        return args -> {
//            Customer client = new Customer(
//                    1,
//                    "Martin",
//                    "Yovchev",
//                    "azSumGei",
//                    "Gei",
//                    "mayovchev@per-store.com",
//                    0
//            );
//
//
//            repository.save(client);
//        };
//    }
//}
