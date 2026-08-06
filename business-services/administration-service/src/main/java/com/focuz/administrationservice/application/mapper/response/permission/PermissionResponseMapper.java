package com.focuz.administrationservice.application.mapper.response.permission;

import com.focuz.administrationservice.application.dto.response.permission.PermissionResponse;
import com.focuz.administrationservice.domain.entity.permission.Permission;
import com.focuz.corestarter.domain.entity.template.mapper.DomainMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface PermissionResponseMapper extends DomainMapper<PermissionResponse, Permission> {
}
