package com.focuz.authservice.infrastructure.persistence.database.mapper.scope;

import com.focuz.authservice.domain.entity.scope.Scope;
import com.focuz.authservice.infrastructure.persistence.database.entity.scope.ScopeEntity;
import com.focuz.corestarter.domain.entity.template.mapper.EntityMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface ScopeEntityMapper extends EntityMapper<ScopeEntity, Scope> {
}
