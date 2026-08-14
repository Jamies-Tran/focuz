package com.focuz.administrationservice.application.mapper.response.client;

import com.focuz.administrationservice.application.dto.response.client.ClientResponse;
import com.focuz.administrationservice.domain.entity.client.Client;
import com.focuz.corestarter.domain.entity.template.mapper.DomainMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface ClientResponseMapper extends DomainMapper<Client, ClientResponse> {
}
