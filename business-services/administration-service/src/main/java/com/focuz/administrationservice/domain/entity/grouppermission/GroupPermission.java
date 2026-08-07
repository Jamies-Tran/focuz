package com.focuz.administrationservice.domain.entity.grouppermission;

import lombok.Builder;

import java.util.List;

@Builder
public record GroupPermission(
        Long groupPermissionId,
        Long authGroupId,
        Long permissionId,
        String permissionCode,
        String permissionName
) {
    public static List<GroupPermission> of(Long authGroupId, List<Long> permissionIds) {
        return permissionIds.stream()
                .map(pid -> GroupPermission.builder()
                        .authGroupId(authGroupId)
                        .permissionId(pid)
                        .build())
                .toList();
    }
}
