package com.focuz.administrationservice.domain.entity.client;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Client(
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
