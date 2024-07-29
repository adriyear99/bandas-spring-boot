package com.springboot.adriano.controller.suporte;

import com.springboot.adriano.dto.suporte.AuthRequest;
import com.springboot.adriano.dto.suporte.AuthResponse;
import com.springboot.adriano.dto.suporte.ChangePasswordRequest;
import com.springboot.adriano.exceptions.AccountExistsException;
import com.springboot.adriano.exceptions.InvalidCredentialException;
import com.springboot.adriano.service.suporte.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest req) {
        try {
            return ResponseEntity.ok(service.criarConta(req));
        } catch (AccountExistsException e) {
            return new ResponseEntity<>(AuthResponse.builder()
                            .sucesso(false).msg(e.getMsg())
                            .build(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        try {
            return ResponseEntity.ok(service.autenticar(request));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(AuthResponse.builder()
                    .sucesso(false).msg("Credenciais inválidas")
                    .build(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }

    @PatchMapping
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request,
                                            Principal connectedUser) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }


}
