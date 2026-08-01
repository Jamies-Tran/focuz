package com.focuz.authstarter;

import com.focuz.authstarter.application.usecase.JwtProviderImpl;
import com.focuz.authstarter.domain.service.JwtProvider;
import com.focuz.authstarter.infrastructure.bootstrap.configuration.security.SecurityConfiguration;
import com.focuz.authstarter.infrastructure.bootstrap.configuration.security.filter.JwtAuthenticationFilter;
import com.focuz.authstarter.infrastructure.bootstrap.configuration.security.handler.CustomAccessDeniedHandler;
import com.focuz.authstarter.infrastructure.bootstrap.configuration.security.handler.CustomAuthenticationEntryPoint;
import com.focuz.authstarter.infrastructure.properties.AuthProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@AutoConfiguration
@EnableConfigurationProperties(AuthProperties.class)
public class AuthAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public JwtProvider jwtProvider(AuthProperties properties) {
        return new JwtProviderImpl(properties);
    }

    @Bean
    @ConditionalOnMissingBean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtProvider jwtProvider) {
        return new JwtAuthenticationFilter(jwtProvider);
    }

    @Bean
    @ConditionalOnMissingBean
    public SecurityFilterChain securityConfiguration(
            JwtAuthenticationFilter filter,
            AccessDeniedHandler accessDeniedHandler,
            AuthenticationEntryPoint entryPoint,
            HttpSecurity http,
            AuthProperties properties
    ) throws Exception {
        return new SecurityConfiguration(filter, accessDeniedHandler, entryPoint, properties).securityFilterChain(http);
    }

    @Bean
    @ConditionalOnMissingBean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
