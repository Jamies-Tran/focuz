package com.focuz.authservice.application.dto.response.application;

import java.time.LocalDateTime;

public record AuthApplicationResponse(
        Long applicationId,
        String applicationCode,
        String applicationName,
        String statusCode,
        String statusName,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
