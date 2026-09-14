package com.focuz.authservice.domain.entity.application;

import java.time.LocalDateTime;

public record AuthApplication(
        Long applicationId,
        String applicationCode,
        String applicationName,
        String statusCode,
        String statusName,
        String search,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
