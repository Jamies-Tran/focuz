package com.focuz.authservice.infrastructure.persistence.database.mapper.clientscope;

import com.focuz.authservice.domain.entity.clientscope.ClientScope;
import com.focuz.authservice.infrastructure.persistence.database.entity.clientscope.ClientScopeDao;
import com.focuz.corestarter.domain.entity.template.mapper.DaoMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface ClientScopeDaoMapper extends DaoMapper<ClientScopeDao, ClientScope> {
}
