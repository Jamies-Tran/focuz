package com.focuz.authservice.application.dto.response.clientscope;

import java.time.LocalDateTime;

public record ClientScopeResponse(
        Long clientScopeId,
        Long clientId,
        Long scopeId,
        String clientCode,
        String clientSecret,
        String redirectUri,
        String scopeCode,
        String scopeName,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
