package com.focuz.authservice.application.mapper.response.application;

import com.focuz.authservice.application.dto.response.application.AuthApplicationResponse;
import com.focuz.authservice.domain.entity.application.AuthApplication;
import com.focuz.corestarter.domain.entity.template.mapper.DomainMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface AuthApplicationResponseMapper extends DomainMapper<AuthApplication, AuthApplicationResponse> {
}
