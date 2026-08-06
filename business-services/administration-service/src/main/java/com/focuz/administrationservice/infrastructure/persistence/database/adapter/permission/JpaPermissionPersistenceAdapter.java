package com.focuz.administrationservice.infrastructure.persistence.database.adapter.permission;

import com.focuz.administrationservice.domain.constant.enums.permission.EPermissionStatus;
import com.focuz.administrationservice.domain.entity.permission.Permission;
import com.focuz.administrationservice.domain.entity.permission.PermissionCriteria;
import com.focuz.administrationservice.domain.repository.permission.PermissionRepository;
import com.focuz.administrationservice.infrastructure.persistence.database.mapper.permission.PermissionEntityMapper;
import com.focuz.administrationservice.infrastructure.persistence.database.repository.permission.JpaPermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JpaPermissionPersistenceAdapter implements PermissionRepository {
    JpaPermissionRepository repository;
    PermissionEntityMapper mapper;

    @Override
    public List<Permission> saveAll(List<Permission> permissions) {
        return mapper.toDomain(repository
                .saveAll(mapper
                        .toEntity(permissions)));
    }

    @Override
    public Optional<Permission> findByPermissionCode(String permissionCode) {
        return repository.findByPermissionCode(permissionCode)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Permission> findAll(PermissionCriteria criteria, PageRequest pageRequest) {
        return repository.findAll(criteria, pageRequest)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Permission> updateByPermissionCode(String permissionCode, Permission permission) {
        return repository.findByPermissionCode(permissionCode)
                .map(p -> {
                    mapper.update(p, permission);
                    return mapper.toDomain(repository.save(p));
                });
    }

    @Override
    public Optional<Permission> updateByPermissionCode(String permissionCode, EPermissionStatus status) {
        return repository.findByPermissionCode(permissionCode)
                .map(p -> {
                    p.setStatusCode(status.getCode());
                    p.setStatusName(status.getName());
                    return mapper.toDomain(repository.save(p));
                });
    }

    @Override
    public void deleteAllByPermissionCodeIn(List<String> permissionCodes) {
        repository.deleteAll(repository.findAllByPermissionCodeIn(permissionCodes));
    }

    @Override
    public Boolean existsAllByPermissionCodeIn(List<String> permissionCodes) {
        return repository.existsAllByPermissionCodeIn(permissionCodes);
    }
}
