package com.focuz.authservice.domain.entity.clientscope;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ClientScope(
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
    public static List<ClientScope> of(Long clientId, List<Long> scopeIds) {
        return scopeIds.stream()
                .map(s -> ClientScope.builder()
                        .clientId(clientId)
                        .scopeId(s)
                        .build())
                .toList();
    }
}
