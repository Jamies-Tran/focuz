package com.focuz.administrationservice.application.usecase.permission;

import com.focuz.administrationservice.domain.constant.enums.error.EAppError;
import com.focuz.administrationservice.domain.constant.enums.permission.EPermissionStatus;
import com.focuz.administrationservice.domain.entity.permission.Permission;
import com.focuz.administrationservice.domain.entity.permission.PermissionCriteria;
import com.focuz.administrationservice.domain.repository.permission.PermissionRepository;
import com.focuz.administrationservice.domain.service.permission.PermissionService;
import com.focuz.corestarter.domain.entity.exception.ApplicationException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionUseCase implements PermissionService {
    PermissionRepository repository;

    @Override
    @Transactional
    public List<Permission> createList(List<Permission> permissions) {
        validateCreateList(permissions);
        return repository.saveAll(permissions);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Permission> getDetailByCode(String permissionCode) {
        return repository.findByPermissionCode(permissionCode);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Permission> getPage(PermissionCriteria criteria) {
        return repository.findAll(criteria, criteria.pageRequest());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Permission> getListByPermissionCodeIn(List<String> permissionCodes) {
        return repository.findAllByPermissionCodeIn(permissionCodes);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Permission> getListByPermissionIdIn(List<Long> permissionIds) {
        return repository.findAllByPermissionIdIn(permissionIds);
    }

    @Override
    @Transactional
    public Permission updateByCode(String permissionCode, Permission permission) {
        validateUpdateByCode(permissionCode, permission);
        return repository.updateByPermissionCode(permissionCode, permission)
                .orElseThrow(() -> new ApplicationException(EAppError.PERMISSION_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public Permission activeByCode(String permissionCode) {
        return repository.updateByPermissionCode(permissionCode, EPermissionStatus.ACTIVE)
                .orElseThrow(() -> new ApplicationException(EAppError.PERMISSION_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public Permission inactiveByCode(String permissionCode) {
        return repository.updateByPermissionCode(permissionCode, EPermissionStatus.INACTIVE)
                .orElseThrow(() -> new ApplicationException(EAppError.PERMISSION_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public void removeListByCodeIn(List<String> permissionCodes) {
        repository.deleteAllByPermissionCodeIn(permissionCodes);
    }

    private void validateCreateList(List<Permission> permissions) {
        List<String> permissionCodes = permissions.stream()
                .map(Permission::permissionCode)
                .filter(StringUtils::hasText)
                .toList();
        if(new HashSet<>(permissionCodes).size() != permissions.stream()
                .filter(p -> StringUtils.hasText(p.permissionCode())).count()) {
            throw new ApplicationException(EAppError.PERMISSION_DUPLICATE_IN_LIST,  HttpStatus.BAD_REQUEST);
        }
        if(repository.existsAllByPermissionCodeIn(permissionCodes)) {
            throw new ApplicationException(EAppError.PERMISSION_DUPLICATE_IN_DB,  HttpStatus.BAD_REQUEST);
        }
    }

    private void validateUpdateByCode(String permissionCode, Permission permission) {
        repository.findByPermissionCode(permissionCode)
                .ifPresent(p -> {
                    if(!Objects.equals(p.permissionCode(), permission.permissionCode())) {
                        if(repository.existsAllByPermissionCodeIn(List.of(permission.permissionCode()))) {
                            throw new ApplicationException(EAppError.PERMISSION_DUPLICATE_IN_DB,  HttpStatus.BAD_REQUEST);
                        }
                    }
                });
    }
}
