package com.internship.tool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // ✅ Disable CSRF (modern way)
            .csrf(csrf -> csrf.disable())

            // ✅ Stateless API (important for REST)
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // ✅ Authorization rules
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/**",
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                ).permitAll()
                .anyRequest().authenticated()
            );

        return http.build();
    }
}