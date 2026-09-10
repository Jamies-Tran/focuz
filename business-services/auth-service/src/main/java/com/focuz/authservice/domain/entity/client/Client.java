package com.focuz.authservice.domain.entity.client;

import java.time.LocalDateTime;

public record Client(
        Long clientId,
        String clientCode,
        String clientSecret,
        String redirectUri,
        String statusCode,
        String statusName,
        String search,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
