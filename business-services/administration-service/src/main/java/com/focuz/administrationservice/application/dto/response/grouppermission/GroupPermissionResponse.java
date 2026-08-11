package com.focuz.administrationservice.application.dto.response.grouppermission;

public record GroupPermissionResponse(
        Long groupPermissionId,
        Long authGroupId,
        Long permissionId,
        String permissionCode,
        String permissionName
) {
}
