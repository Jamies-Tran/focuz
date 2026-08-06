package com.focuz.administrationservice.infrastructure.persistence.database.repository.permission;

import com.focuz.administrationservice.domain.entity.permission.PermissionCriteria;
import com.focuz.administrationservice.infrastructure.persistence.database.entity.permission.PermissionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaPermissionRepository extends JpaRepository<PermissionEntity, Long> {
    Optional<PermissionEntity> findByPermissionCode(String permissionCode);
    List<PermissionEntity> findAllByPermissionCodeIn(List<String> permissionCodes);
    List<PermissionEntity> findAllByPermissionIdIn(List<Long> permissionIds);
    Boolean existsAllByPermissionCodeIn(List<String> permissionCodes);
    @Query("""
        SELECT p
        FROM PermissionEntity p
        WHERE (:#{#criteria.search().empty} = TRUE
                OR p.search LIKE %:#{#criteria.search()}%)
         AND (:#{#criteria.permissionCodes().empty} = TRUE
            OR p.permissionCode IN :#{#criteria.permissionCodes()})
        AND (:#{#criteria.statusCodes().empty} = TRUE
                OR p.statusCode IN :#{#criteria.statusCodes()})
        """)
    Page<PermissionEntity> findAll(PermissionCriteria criteria, Pageable pageable);
}
