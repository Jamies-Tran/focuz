package com.focuz.administrationservice.application.dto.response.authgroup;

import java.time.LocalDateTime;
import java.util.List;

public record AuthGroupResponse(
        Long authGroupId,
        String authGroupCode,
        String authGroupName,
        List<PermissionResponse> permissions,
        String statusCode,
        String statusName,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public record PermissionResponse(
            Long permissionId,
            String permissionCode,
            String permissionName
    ) {}
}
