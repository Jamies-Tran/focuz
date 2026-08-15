package com.focuz.administrationservice.domain.repository.grouppermission;

import com.focuz.administrationservice.domain.entity.grouppermission.GroupPermission;
import com.focuz.administrationservice.domain.entity.grouppermission.GroupPermissionCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface GroupPermissionRepository {
    List<GroupPermission> saveAll(List<GroupPermission> groupPermissions);
    Page<GroupPermission> findAll(GroupPermissionCriteria criteria, PageRequest pageRequest);
    List<Long> findAllGroupPermissionIdByAuthGroupCodeAndPermissionCodeIn(String authGroupCode, List<String> permissionCodes);
    void deleteAllByGroupPermissionIdIn(List<Long> groupPermissionIds);
}
