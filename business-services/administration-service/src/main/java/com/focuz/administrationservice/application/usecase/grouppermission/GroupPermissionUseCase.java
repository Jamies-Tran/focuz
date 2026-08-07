package com.focuz.administrationservice.application.usecase.grouppermission;

import com.focuz.administrationservice.domain.entity.grouppermission.GroupPermission;
import com.focuz.administrationservice.domain.entity.permission.Permission;
import com.focuz.administrationservice.domain.repository.grouppermission.GroupPermissionRepository;
import com.focuz.administrationservice.domain.service.grouppermission.GroupPermissionService;
import com.focuz.administrationservice.domain.service.permission.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GroupPermissionUseCase implements GroupPermissionService {
    GroupPermissionRepository repository;
    PermissionService permissionService;

    @Override
    @Transactional
    public List<GroupPermission> createList(Long authGroupId, List<String> permissionCodes) {
        List<Long> permissionIds = permissionService.getListByPermissionCodeIn(permissionCodes)
                .stream()
                .map(Permission::permissionId)
                .toList();
        return repository.saveAll(GroupPermission.of(authGroupId, permissionIds));
    }

    @Override
    public List<GroupPermission> getGroupPermissionListByGroupId(Long groupId) {
        return repository.findAllByAuthGroupId(groupId);
    }

    @Override
    public List<GroupPermission> getGroupPermissionListByGroupIdIn(List<Long> groupIds) {
        return repository.findAllByAuthGroupIdIn(groupIds);
    }
}
