package com.focuz.administrationservice.application.dto.response.permission;

import java.time.LocalDateTime;

public record PermissionResponse(
        Long permissionId,
        String permissionCode,
        String permissionName,
        String statusCode,
        String statusName,
        String search,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
