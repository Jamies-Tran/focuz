package com.focuz.administrationservice.domain.repository.grouppermission;

import com.focuz.administrationservice.domain.entity.grouppermission.GroupPermission;

import java.util.List;

public interface GroupPermissionRepository {
    List<GroupPermission> saveAll(List<GroupPermission> groupPermissions);
    List<GroupPermission> findAllByAuthGroupId(Long groupId);
    List<GroupPermission> findAllByAuthGroupIdIn(List<Long> groupIds);
}
