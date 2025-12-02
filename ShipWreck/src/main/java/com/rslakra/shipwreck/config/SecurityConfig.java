package com.rslakra.shipwreck.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration for the application.
 * Allows H2 console access and configures basic authentication.
 *
 * @author Rohtash Lakra
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configure security filter chain.
     *
     * @param http HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Allow H2 console
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                // Allow API endpoints
                .requestMatchers("/api/**").permitAll()
                .requestMatchers("/rest/**").permitAll()
                .requestMatchers("/public/**").permitAll()
                .requestMatchers("/home").permitAll()
                // All other requests require authentication
                .anyRequest().authenticated()
            )
            // Disable CSRF for H2 console and API
            .csrf(csrf -> csrf
                .ignoringRequestMatchers(PathRequest.toH2Console())
                .ignoringRequestMatchers("/api/**")
                .ignoringRequestMatchers("/rest/**")
            )
            // Allow frames from same origin for H2 console
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.sameOrigin())
            )
            .httpBasic(basic -> { });

        return http.build();
    }
}
