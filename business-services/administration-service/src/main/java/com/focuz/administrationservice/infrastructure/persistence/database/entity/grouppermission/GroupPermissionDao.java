package com.focuz.administrationservice.infrastructure.persistence.database.entity.grouppermission;

public interface GroupPermissionDao {
    Long getGroupPermissionId();
    Long getAuthGroupId();
    Long getPermissionId();
    String getPermissionCode();
    String getPermissionName();
}
