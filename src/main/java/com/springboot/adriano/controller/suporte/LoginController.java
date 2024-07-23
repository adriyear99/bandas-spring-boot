package com.springboot.adriano.controller.suporte;

import com.springboot.adriano.dto.suporte.AuthRequest;
import com.springboot.adriano.dto.suporte.AuthResponse;
import com.springboot.adriano.dto.suporte.LoginRequest;
import com.springboot.adriano.service.suporte.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> criarConta(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.criarConta(request));
    }

    @PostMapping("/autenticar")
    public ResponseEntity<AuthResponse> fazerLogin(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.autenticar(request));
    }
}
