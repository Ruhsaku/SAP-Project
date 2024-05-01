//package com.racooncoding.perfumestore.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    // TODO --> Fix every login, register and redirecting functionality
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((authz) -> authz
//                        .requestMatchers("/home", "/checkout", "/dashboard", "/charge", "/cart",
//                                "/dashboard/getAllProducts", "/dashboard/addProduct",
//                                "/dashboard/removeProduct", "/dashboard/updateProduct")
//                            .authenticated()
//                        .requestMatchers("/login", "/login/employee", "/register", "/")
//                            .permitAll()
//                        .anyRequest().authenticated()
//                ).formLogin(form -> form
//                        .loginPage("/login")
//                            .permitAll()
//                        .successForwardUrl("/home"));
//        return http.build();
//    }
//}