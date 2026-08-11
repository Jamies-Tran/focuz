package com.focuz.administrationservice.infrastructure.persistence.database.mapper.client;

import com.focuz.administrationservice.domain.entity.client.Client;
import com.focuz.administrationservice.infrastructure.persistence.database.entity.client.ClientEntity;
import com.focuz.corestarter.domain.entity.template.mapper.EntityMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface ClientEntityMapper extends EntityMapper<ClientEntity, Client> {
}
