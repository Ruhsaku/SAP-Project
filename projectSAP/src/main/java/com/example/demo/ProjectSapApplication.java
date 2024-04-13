package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@RequestMapping("/")
@EnableAsync
public class ProjectSapApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectSapApplication.class, args);
	}

}
