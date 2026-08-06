package com.focuz.administrationservice.infrastructure.persistence.database.mapper.grouppermission;

import com.focuz.administrationservice.domain.entity.grouppermission.GroupPermission;
import com.focuz.administrationservice.infrastructure.persistence.database.entity.grouppermission.GroupPermissionEntity;
import com.focuz.corestarter.domain.entity.template.mapper.EntityMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface GroupPermissionEntityMapper extends EntityMapper<GroupPermissionEntity, GroupPermission> {
}
