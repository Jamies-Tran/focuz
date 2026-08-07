package com.focuz.administrationservice.infrastructure.persistence.database.adapter.grouppermission;

import com.focuz.administrationservice.domain.entity.grouppermission.GroupPermission;
import com.focuz.administrationservice.domain.repository.grouppermission.GroupPermissionRepository;
import com.focuz.administrationservice.infrastructure.persistence.database.mapper.grouppermission.GroupPermissionDaoMapper;
import com.focuz.administrationservice.infrastructure.persistence.database.mapper.grouppermission.GroupPermissionEntityMapper;
import com.focuz.administrationservice.infrastructure.persistence.database.repository.grouppermission.JpaGroupPermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JpaGroupPermissionPersistenceAdapter implements GroupPermissionRepository {
    JpaGroupPermissionRepository repository;
    GroupPermissionEntityMapper mapper;
    GroupPermissionDaoMapper daoMapper;

    @Override
    public List<GroupPermission> saveAll(List<GroupPermission> groupPermissions) {
        return mapper.toDomain(repository.saveAll(mapper.toEntity(groupPermissions)));
    }

    @Override
    public List<GroupPermission> findAllByAuthGroupId(Long groupId) {
        return daoMapper.toDomain(repository.findAllByAuthGroupId(groupId));
    }

    @Override
    public List<GroupPermission> findAllByAuthGroupIdIn(List<Long> groupIds) {
        return daoMapper.toDomain(repository.findAllByAuthGroupIdIn(groupIds));
    }
}
