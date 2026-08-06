package com.focuz.administrationservice.infrastructure.persistence.database.repository.grouppermission;

import com.focuz.administrationservice.infrastructure.persistence.database.entity.grouppermission.GroupPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaGroupPermissionRepository extends JpaRepository<GroupPermissionEntity, Long> {
    List<GroupPermissionEntity> findAllByAuthGroupId(Long groupId);
    List<GroupPermissionEntity> findAllByAuthGroupIdIn(List<Long> groupIds);
}
