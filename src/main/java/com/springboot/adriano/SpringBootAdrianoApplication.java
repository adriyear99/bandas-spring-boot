package com.springboot.adriano;

import com.springboot.adriano.dto.suporte.AuthRequest;
import com.springboot.adriano.service.suporte.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import static com.springboot.adriano.enums.Role.ADMIN;
import static com.springboot.adriano.enums.Role.MANAGER;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SpringBootAdrianoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAdrianoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UsuarioService service) {
		return args -> {
			AuthRequest admin = AuthRequest.builder()
					.email("admin@mail.com")
					.senha("password")
					.build();
			System.out.println("Admin token: " + service.criarConta(admin).getAccessToken());

			AuthRequest manager = AuthRequest.builder()
					.email("manager@mail.com")
					.senha("password")
					.build();
			System.out.println("Manager token: " + service.criarConta(manager).getAccessToken());
		};
	}

}
