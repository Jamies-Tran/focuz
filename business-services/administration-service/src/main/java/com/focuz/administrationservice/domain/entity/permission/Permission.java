package com.focuz.administrationservice.domain.entity.permission;

import com.focuz.administrationservice.domain.entity.grouppermission.GroupPermission;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
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
    public static List<Permission> of(List<GroupPermission> groupPermissions) {
        return groupPermissions.stream()
                .map(gp -> Permission.builder()
                        .permissionId(gp.permissionId())
                        .permissionCode(gp.permissionCode())
                        .permissionName(gp.permissionName())
                        .build())
                .toList();
    }

    public static Permission of(GroupPermission groupPermission) {
        return Permission.builder()
                .permissionId(groupPermission.permissionId())
                .permissionCode(groupPermission.permissionCode())
                .permissionName(groupPermission.permissionName())
                .build();
    }
}
