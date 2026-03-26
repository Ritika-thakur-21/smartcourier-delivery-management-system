package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        return http
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())

                // Let JwtUtil GlobalFilter handle JWT validation.
                // SecurityConfig only declares which paths are public.
                .authorizeExchange(exchange -> exchange
                        .pathMatchers(
                                "/auth/**",                  // auth-service direct
                                "/gateway/auth/**",          // auth-service via gateway
                                "/v3/api-docs/**",           // swagger docs direct
                                "/gateway/*/v3/api-docs",    // swagger docs via gateway (e.g. /gateway/admin/v3/api-docs)
                                "/gateway/*/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/webjars/**",
                                "/fallback/**"
                        ).permitAll()
                        .anyExchange().permitAll()           // JWT enforcement handled by JwtUtil GlobalFilter
                )
                .build();
    }
}
