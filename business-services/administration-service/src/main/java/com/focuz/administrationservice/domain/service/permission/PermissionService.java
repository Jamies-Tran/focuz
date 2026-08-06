package com.focuz.administrationservice.domain.service.permission;

import com.focuz.administrationservice.domain.entity.permission.Permission;
import com.focuz.administrationservice.domain.entity.permission.PermissionCriteria;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PermissionService {
    List<Permission> createList(List<Permission> permissions);
    Optional<Permission> getDetailByCode(String permissionCode);
    Page<Permission> getPage(PermissionCriteria criteria);
    Permission updateByCode(String permissionCode, Permission permission);
    Permission activeByCode(String permissionCode);
    Permission inactiveByCode(String permissionCode);
    void removeListByCodeIn(List<String> permissionCodes);
}
