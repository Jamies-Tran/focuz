package com.focuz.authservice.domain.entity.scope;

import java.time.LocalDateTime;

public record Scope(
        Long scopeId,
        String scopeCode,
        String scopeName,
        String search,
        String statusCode,
        String statusName,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
