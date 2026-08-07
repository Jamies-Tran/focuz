package com.focuz.administrationservice.infrastructure.persistence.database.mapper.grouppermission;

import com.focuz.administrationservice.domain.entity.grouppermission.GroupPermission;
import com.focuz.administrationservice.infrastructure.persistence.database.entity.grouppermission.GroupPermissionDao;
import com.focuz.corestarter.domain.entity.template.mapper.DaoMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface GroupPermissionDaoMapper extends DaoMapper<GroupPermissionDao, GroupPermission> {
}
