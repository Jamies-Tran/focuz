package com.focuz.administrationservice.application.mapper.response.authgroup;

import com.focuz.administrationservice.application.dto.response.authgroup.AuthGroupResponse;
import com.focuz.administrationservice.domain.entity.authgroup.AuthGroup;
import com.focuz.corestarter.domain.entity.template.mapper.DomainMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface AuthGroupResponseMapper extends DomainMapper<AuthGroupResponse, AuthGroup> {
}
