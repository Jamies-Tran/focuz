package com.focuz.administrationservice.domain.entity.permission;

import java.time.LocalDateTime;

public record Permission(
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
