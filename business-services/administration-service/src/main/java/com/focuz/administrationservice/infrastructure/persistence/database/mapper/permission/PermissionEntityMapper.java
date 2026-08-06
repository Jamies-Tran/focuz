package com.focuz.administrationservice.infrastructure.persistence.database.mapper.permission;

import com.focuz.administrationservice.domain.entity.permission.Permission;
import com.focuz.administrationservice.infrastructure.persistence.database.entity.permission.PermissionEntity;
import com.focuz.corestarter.domain.entity.template.mapper.EntityMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface PermissionEntityMapper extends EntityMapper<PermissionEntity, Permission> {
}
