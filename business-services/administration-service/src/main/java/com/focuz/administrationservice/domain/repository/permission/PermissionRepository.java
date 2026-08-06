package com.focuz.administrationservice.domain.repository.permission;

import com.focuz.administrationservice.domain.constant.enums.permission.EPermissionStatus;
import com.focuz.administrationservice.domain.entity.permission.Permission;
import com.focuz.administrationservice.domain.entity.permission.PermissionCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository {
    List<Permission> saveAll(List<Permission> permissions);
    Optional<Permission> findByPermissionCode(String permissionCode);
    Page<Permission> findAll(PermissionCriteria criteria, PageRequest pageRequest);
    List<Permission> findAllByPermissionCodeIn(List<String> permissionCodes);
    List<Permission> findAllByPermissionIdIn(List<Long> permissionIds);
    Optional<Permission> updateByPermissionCode(String permissionCode, Permission permission);
    Optional<Permission> updateByPermissionCode(String permissionCode, EPermissionStatus status);
    void deleteAllByPermissionCodeIn(List<String> permissionCodes);
    Boolean existsAllByPermissionCodeIn(List<String> permissionCodes);
}
