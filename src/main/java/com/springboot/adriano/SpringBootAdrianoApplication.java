package com.springboot.adriano;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SpringBootAdrianoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAdrianoApplication.class, args);
	}

}
