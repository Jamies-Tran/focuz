package com.focuz.authservice.application.dto.response.scope;

import java.time.LocalDateTime;

public record ScopeResponse(
        Long scopeId,
        String scopeCode,
        String scopeName,
        String statusCode,
        String statusName,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
