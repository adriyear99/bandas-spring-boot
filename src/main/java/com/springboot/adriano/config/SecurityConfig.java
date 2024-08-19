package com.springboot.adriano.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        .authorizeHttpRequests(req -> req.requestMatchers("/api/auth/**").permitAll()
//        .anyRequest.authenticated())
        http.csrf(AbstractHttpConfigurer::disable)
           .authorizeHttpRequests(req -> req.anyRequest().permitAll())
           .sessionManagement(sess -> sess.sessionCreationPolicy(STATELESS))
           .authenticationProvider(authenticationProvider).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
           .logout(logout -> logout.logoutUrl("/api/auth/logout").addLogoutHandler(logoutHandler)
           .logoutSuccessHandler((req,res,auth) -> SecurityContextHolder.clearContext()));

        return http.build();
    }
}
