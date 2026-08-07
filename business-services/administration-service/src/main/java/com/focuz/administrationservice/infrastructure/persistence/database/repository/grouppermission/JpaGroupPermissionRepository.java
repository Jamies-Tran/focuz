package com.focuz.administrationservice.infrastructure.persistence.database.repository.grouppermission;

import com.focuz.administrationservice.infrastructure.persistence.database.entity.grouppermission.GroupPermissionDao;
import com.focuz.administrationservice.infrastructure.persistence.database.entity.grouppermission.GroupPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaGroupPermissionRepository extends JpaRepository<GroupPermissionEntity, Long> {
    @Query("""
        SELECT 
                gp.groupPermissionId AS groupPermissionId,
                gp.authGroupId AS authGroupId,
                gp.permissionId AS permissionId,
                p.permissionCode AS permissionCode,
                p.permissionName AS permissionName
        FROM GroupPermissionEntity gp
        INNER JOIN PermissionEntity p ON gp.permissionId = p.permissionId
        WHERE gp.authGroupId = :groupId
        """)
    List<GroupPermissionDao> findAllByAuthGroupId(Long groupId);
    @Query("""
        SELECT 
                gp.groupPermissionId AS groupPermissionId,
                gp.authGroupId AS authGroupId,
                gp.permissionId AS permissionId,
                p.permissionCode AS permissionCode,
                p.permissionName AS permissionName
        FROM GroupPermissionEntity gp
        INNER JOIN PermissionEntity p ON gp.permissionId = p.permissionId
        WHERE gp.authGroupId IN :groupIds
        """)
    List<GroupPermissionDao> findAllByAuthGroupIdIn(List<Long> groupIds);
}
