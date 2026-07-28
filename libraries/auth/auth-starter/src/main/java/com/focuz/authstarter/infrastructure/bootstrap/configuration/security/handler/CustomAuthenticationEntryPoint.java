package com.focuz.authstarter.infrastructure.bootstrap.configuration.security.handler;

import com.focuz.authstarter.infrastructure.bootstrap.configuration.jackson.JacksonMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.Map;

@Configuration
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        JacksonMapper.INSTANCE.getObjectMapper()
                .writeValue(
                        response.getOutputStream(),
                        Map.of(
                                "status", "403",
                                "message", "Truy cập của bạn bị từ chối"
                        )
                );
    }
}
