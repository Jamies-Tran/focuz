package com.focuz.administrationservice.domain.service.grouppermission;

import com.focuz.administrationservice.domain.entity.grouppermission.GroupPermission;
import com.focuz.administrationservice.domain.entity.grouppermission.GroupPermissionCriteria;
import com.focuz.administrationservice.domain.entity.permission.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface GroupPermissionService {
    List<GroupPermission> createList(Long authGroupId, List<String> permissionCodes);
    Page<GroupPermission> getPagePermissions(GroupPermissionCriteria criteria);
}
