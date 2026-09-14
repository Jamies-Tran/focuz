package com.focuz.authservice.application.mapper.response.clientscope;

import com.focuz.authservice.application.dto.response.clientscope.ClientScopeResponse;
import com.focuz.authservice.domain.entity.clientscope.ClientScope;
import com.focuz.corestarter.domain.entity.template.mapper.DomainMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface ClientScopeResponseMapper extends DomainMapper<ClientScope, ClientScopeResponse> {
}
