package com.focuz.administrationservice.infrastructure.persistence.database.repository.grouppermission;

import com.focuz.administrationservice.domain.entity.grouppermission.GroupPermissionCriteria;
import com.focuz.administrationservice.infrastructure.persistence.database.entity.grouppermission.GroupPermissionDao;
import com.focuz.administrationservice.infrastructure.persistence.database.entity.grouppermission.GroupPermissionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaGroupPermissionRepository extends JpaRepository<GroupPermissionEntity, Long> {
    @Query("""
        SELECT DISTINCT gp.groupPermissionId
        FROM GroupPermissionEntity gp
        INNER JOIN AuthGroupEntity ag ON ag.authGroupId = gp.authGroupId
        INNER JOIN PermissionEntity p ON p.permissionId = gp.permissionId
        WHERE ag.authGroupCode = :authGroupCode
                AND p.permissionCode IN :permissionCodes
        """)
    List<Long> findAllGroupPermissionIdByAuthGroupCodeAndPermissionCodeIn(String authGroupCode, List<String> permissionCodes);
    @Query("""
        SELECT 
                gp.groupPermissionId AS groupPermissionId,
                gp.authGroupId AS authGroupId,
                gp.permissionId AS permissionId,
                p.permissionCode AS permissionCode,
                p.permissionName AS permissionName
        FROM GroupPermissionEntity gp
        INNER JOIN PermissionEntity p ON gp.permissionId = p.permissionId
        WHERE gp.authGroupId = :#{#criteria.authGroupId()}
            AND (:#{#criteria.search().empty} = TRUE
                    OR p.search LIKE %:#{#criteria.search()}%)
           AND (:#{#criteria.permissionCodes().empty} = TRUE
                   OR p.permissionCode IN :#{#criteria.permissionCodes()})
        """)
    Page<GroupPermissionDao> findAll(GroupPermissionCriteria criteria, Pageable pageable);
}
