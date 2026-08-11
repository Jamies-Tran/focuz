package com.focuz.administrationservice.application.mapper.response.usergroup;

import com.focuz.administrationservice.application.dto.response.usergroup.UserGroupResponse;
import com.focuz.administrationservice.domain.entity.usergroup.UserGroup;
import com.focuz.corestarter.domain.entity.template.mapper.DomainMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface UserGroupResponseMapper extends DomainMapper<UserGroupResponse, UserGroup> {
}
