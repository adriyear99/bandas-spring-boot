package com.springboot.adriano.service.suporte;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.adriano.dto.suporte.AuthRequest;
import com.springboot.adriano.dto.suporte.AuthResponse;
import com.springboot.adriano.dto.suporte.ChangePasswordRequest;
import com.springboot.adriano.entity.suporte.Usuario;
import com.springboot.adriano.entity.suporte.Token;
import com.springboot.adriano.enums.Role;
import com.springboot.adriano.enums.Status;
import com.springboot.adriano.enums.TokenType;
import com.springboot.adriano.repository.suporte.TokenRepository;
import com.springboot.adriano.repository.suporte.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse criarConta(AuthRequest request) {
        Usuario user = Usuario.builder()
                .email(request.getEmail())
                .senha(passwordEncoder.encode(request.getSenha()))
                .role(Role.USER)
                .status(Status.ATIVO)
                .dataHoraCriacao(LocalDateTime.now())
                .build();
        Usuario savedUser = usuarioRepository.save(user);
        String jwtToken = tokenService.gerarToken(user);
        String refreshToken = tokenService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthResponse autenticar(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha())
        );
        Usuario user = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();
        String jwtToken = tokenService.gerarToken(user);
        String refreshToken = tokenService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
    }

    private void saveUserToken(Usuario user, String jwtToken) {
        var token = Token.builder()
                .usuario(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(Usuario user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUsuario(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = tokenService.extractUsername(refreshToken);
        if (userEmail != null) {
            Usuario user = this.usuarioRepository.findByEmail(userEmail).orElseThrow();
            if (tokenService.isTokenValid(refreshToken, user)) {
                String accessToken = tokenService.gerarToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                AuthResponse authResponse = AuthResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        Usuario user = (Usuario) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        if (!passwordEncoder.matches(request.getSenhaAtual(), user.getPassword())) {
            throw new IllegalStateException("Senha incorreta");
        }
        if (!request.getNovaSenha().equals(request.getConfirmacaoSenha())) {
            throw new IllegalStateException("As senhas são diferentes");
        }
        user.setSenha(passwordEncoder.encode(request.getNovaSenha()));
        usuarioRepository.save(user);
    }
}

