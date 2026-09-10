package com.focuz.authservice.application.mapper.request.client;

import com.focuz.authservice.application.dto.request.client.ClientRequest;
import com.focuz.authservice.domain.entity.client.Client;
import com.focuz.corestarter.domain.entity.template.mapper.DomainMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface ClientRequestMapper extends DomainMapper<Client, ClientRequest> {
}
