package com.focuz.administrationservice.infrastructure.persistence.database.mapper.usergroup;

import com.focuz.administrationservice.domain.entity.usergroup.UserGroup;
import com.focuz.administrationservice.infrastructure.persistence.database.entity.usergroup.UserGroupEntity;
import com.focuz.corestarter.domain.entity.template.mapper.EntityMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface UserGroupEntityMapper extends EntityMapper<UserGroupEntity, UserGroup> {
}
