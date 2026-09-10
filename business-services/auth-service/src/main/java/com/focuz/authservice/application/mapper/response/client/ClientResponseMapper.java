package com.focuz.authservice.application.mapper.response.client;

import com.focuz.authservice.application.dto.response.client.ClientResponse;
import com.focuz.authservice.domain.entity.client.Client;
import com.focuz.corestarter.domain.entity.template.mapper.DomainMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface ClientResponseMapper extends DomainMapper<Client, ClientResponse> {
}
