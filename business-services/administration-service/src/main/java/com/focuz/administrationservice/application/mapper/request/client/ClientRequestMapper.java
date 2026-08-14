package com.focuz.administrationservice.application.mapper.request.client;

import com.focuz.administrationservice.application.dto.request.client.ClientRequest;
import com.focuz.administrationservice.domain.entity.client.Client;
import com.focuz.corestarter.domain.entity.template.mapper.DomainMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface ClientRequestMapper extends DomainMapper<Client, ClientRequest> {
}
