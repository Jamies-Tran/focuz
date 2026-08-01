package com.focuz.authstarter.infrastructure.bootstrap.configuration.security;

import com.focuz.authstarter.infrastructure.bootstrap.configuration.security.filter.JwtAuthenticationFilter;
import com.focuz.authstarter.infrastructure.properties.AuthProperties;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.CollectionUtils;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityConfiguration {
    JwtAuthenticationFilter jwtAuthenticationFilter;
    AccessDeniedHandler accessDeniedHandler;
    AuthenticationEntryPoint authenticationEntryPoint;
    AuthProperties properties;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(CsrfConfigurer::disable)
                .cors(CorsConfigurer::disable)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> {
                    if(CollectionUtils.isEmpty(properties.getPermitAll())) {
                        authorize.anyRequest().authenticated();
                    } else {
                        String[] permitAllPaths = properties.getPermitAll().toArray(new String[0]);
                        authorize.requestMatchers(permitAllPaths).permitAll();
                    }
                })
                .exceptionHandling(exc -> exc
                        .accessDeniedHandler(accessDeniedHandler)
                        .authenticationEntryPoint(authenticationEntryPoint))
                .build();
    }
}
