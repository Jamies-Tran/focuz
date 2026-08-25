package com.focuz.iscstarter.infrastructure.client.administration.mapper.response;

import com.focuz.corestarter.domain.entity.template.mapper.DomainMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import com.focuz.iscstarter.domain.entity.administration.user.User;
import com.focuz.iscstarter.infrastructure.client.administration.dto.response.user.UserResponse;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface UserDomainMapper extends DomainMapper<User, UserResponse> {
}
