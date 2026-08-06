package com.focuz.administrationservice.application.mapper.request.permission;

import com.focuz.administrationservice.application.dto.request.permission.PermissionRequest;
import com.focuz.administrationservice.domain.entity.permission.Permission;
import com.focuz.corestarter.domain.entity.template.mapper.DomainMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface PermissionRequestMapper extends DomainMapper<PermissionRequest, Permission> {
}
