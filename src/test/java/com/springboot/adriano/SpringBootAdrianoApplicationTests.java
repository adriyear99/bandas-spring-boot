package com.springboot.adriano;

import com.springboot.adriano.dto.suporte.AuthRequest;
import com.springboot.adriano.service.suporte.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

@SpringBootTest
class SpringBootAdrianoApplicationTests {

	@Test
	void contextLoads() {
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(UsuarioService service) {
//		return args -> {
//			AuthRequest admin = AuthRequest.builder()
//					.email("admin@mail.com")
//					.senha("password")
//					.build();
//			System.out.println("Admin token: " + service.criarConta(admin).getAccessToken());
//
//			AuthRequest manager = AuthRequest.builder()
//					.email("manager@mail.com")
//					.senha("password")
//					.build();
//			System.out.println("Manager token: " + service.criarConta(manager).getAccessToken());
//		};
//	}

}
