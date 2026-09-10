package com.focuz.authservice.infrastructure.persistence.database.mapper.client;

import com.focuz.authservice.domain.entity.client.Client;
import com.focuz.authservice.infrastructure.persistence.database.entity.client.ClientEntity;
import com.focuz.corestarter.domain.entity.template.mapper.EntityMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface ClientEntityMapper extends EntityMapper<ClientEntity, Client> {
}
