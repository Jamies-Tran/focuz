package com.focuz.authservice.application.mapper.response.scope;

import com.focuz.authservice.application.dto.response.scope.ScopeResponse;
import com.focuz.authservice.domain.entity.scope.Scope;
import com.focuz.corestarter.domain.entity.template.mapper.DomainMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface ScopeResponseMapper extends DomainMapper<Scope, ScopeResponse> {
}
