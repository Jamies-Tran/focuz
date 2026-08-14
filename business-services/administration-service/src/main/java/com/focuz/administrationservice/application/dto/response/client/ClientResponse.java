package com.focuz.administrationservice.application.dto.response.client;

import java.time.LocalDateTime;

public record ClientResponse(
        Long clientId,
        String clientCode,
        String clientName,
        String clientSecret,
        String redirectUri,
        String search,
        String statusCode,
        String statusName,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
