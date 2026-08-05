package com.focuz.administrationservice.infrastructure.persistence.database.mapper.authgroup;

import com.focuz.administrationservice.domain.entity.authgroup.AuthGroup;
import com.focuz.administrationservice.infrastructure.persistence.database.entity.authgroup.AuthGroupEntity;
import com.focuz.corestarter.domain.entity.template.mapper.EntityMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface AuthGroupEntityMapper extends EntityMapper<AuthGroupEntity, AuthGroup> {
}
