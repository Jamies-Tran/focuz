package com.focuz.administrationservice.domain.service.grouppermission;

import com.focuz.administrationservice.domain.entity.grouppermission.GroupPermission;
import com.focuz.administrationservice.domain.entity.permission.Permission;

import java.util.List;

public interface GroupPermissionService {
    List<GroupPermission> createList(Long authGroupId, List<String> permissionCodes);
    List<Permission> getPermissionListByGroupId(Long groupId);
    List<GroupPermission> getPermissionListByGroupIdIn(List<Long> groupIds);
}
