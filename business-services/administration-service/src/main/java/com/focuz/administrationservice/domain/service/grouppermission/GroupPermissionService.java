package com.focuz.administrationservice.domain.service.grouppermission;

import com.focuz.administrationservice.domain.entity.grouppermission.GroupPermission;

import java.util.List;

public interface GroupPermissionService {
    List<GroupPermission> createList(Long authGroupId, List<String> permissionCodes);
    List<GroupPermission> getGroupPermissionListByGroupId(Long groupId);
    List<GroupPermission> getGroupPermissionListByGroupIdIn(List<Long> groupIds);
}
