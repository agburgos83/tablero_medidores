package com.tablero_medidores.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // REST -> no CSRF
            .headers(headers -> headers.frameOptions(frame -> frame.disable())) // 🔑 necesario para iframes (H2 usa frames)
            .cors(cors -> {}) // usa tu CorsConfig
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/kpis").permitAll()  // APIs públicas
                .requestMatchers("/h2-console/**").permitAll() // 🔑 habilitar H2
                .anyRequest().authenticated()
            );

        return http.build();
    }
}

