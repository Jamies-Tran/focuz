package com.focuz.authservice.application.mapper.request.application;

import com.focuz.authservice.application.dto.request.application.AuthApplicationRequest;
import com.focuz.authservice.domain.entity.application.AuthApplication;
import com.focuz.corestarter.domain.entity.template.mapper.DomainMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface AuthApplicationRequestMapper extends DomainMapper<AuthApplication, AuthApplicationRequest> {
}
