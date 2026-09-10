package com.focuz.authservice.application.mapper.response.authentication.code;

import com.focuz.authservice.application.dto.response.authentication.code.AuthenticationCodeResponse;
import com.focuz.authservice.domain.entity.authentication.code.AuthenticationCode;
import com.focuz.corestarter.domain.entity.template.mapper.DomainMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface AuthenticationCodeResponseMapper extends DomainMapper<AuthenticationCode, AuthenticationCodeResponse> {
}
