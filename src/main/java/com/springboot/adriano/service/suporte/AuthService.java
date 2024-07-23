package com.springboot.adriano.service.suporte;

import com.springboot.adriano.config.JwtService;
import com.springboot.adriano.dto.suporte.AuthRequest;
import com.springboot.adriano.dto.suporte.AuthResponse;
import com.springboot.adriano.dto.suporte.LoginRequest;
import com.springboot.adriano.entity.Role;
import com.springboot.adriano.entity.Usuario;
import com.springboot.adriano.repository.suporte.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse criarConta(LoginRequest request) {
        var user = Usuario
                .builder()
                .email(request.getEmail())
                .senha(passwordEncoder.encode(request.getSenha()))
                .role(Role.USER)
                .build();

        usuarioRepository.save(user);
        var token = jwtService.gerarToken(user);
        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse autenticar(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha())
        );
        var user = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();
        var token = jwtService.gerarToken(user);
        return AuthResponse.builder().token(token).build();
    }
}
