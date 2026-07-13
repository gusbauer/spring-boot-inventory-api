package com.hibernate.ferreteria.Seguridad;

import com.hibernate.ferreteria.Servicios.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UsuarioService userService;

    public SecurityConfig(UsuarioService userService) {
        this.userService = userService;
    }

    @Bean
    public PasswordEncoder codificaPass() {
        // Esto desactiva BCrypt temporalmente para pruebas
        return org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
    }

    // Configuración explícita para obligar a Spring a unir tu Servicio con tu Encriptador
    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder, UsuarioService usuarioService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usuarioService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(List.of(authProvider));
    }

    @Bean
    public SecurityFilterChain securityChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/articulos/**").authenticated()
                        .anyRequest().authenticated()
                )
                .authenticationManager(authManager)
                .httpBasic(basic -> {});

        return http.build();
    }
}