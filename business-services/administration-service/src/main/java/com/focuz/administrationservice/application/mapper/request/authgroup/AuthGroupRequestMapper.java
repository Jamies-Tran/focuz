package com.focuz.administrationservice.application.mapper.request.authgroup;

import com.focuz.administrationservice.application.dto.request.authgroup.AuthGroupRequest;
import com.focuz.administrationservice.domain.entity.authgroup.AuthGroup;
import com.focuz.corestarter.domain.entity.template.mapper.DomainMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface AuthGroupRequestMapper extends DomainMapper<AuthGroupRequest, AuthGroup> {
}
