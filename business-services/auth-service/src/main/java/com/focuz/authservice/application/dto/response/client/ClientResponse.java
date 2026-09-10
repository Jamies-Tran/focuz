package com.focuz.authservice.application.dto.response.client;

import java.time.LocalDateTime;

public record ClientResponse(
        Long clientId,
        String clientCode,
        String clientSecret,
        String redirectUri,
        String statusCode,
        String statusName,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) { }
