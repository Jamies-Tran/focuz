package com.focuz.administrationservice.application.mapper.request.user;

import com.focuz.administrationservice.application.dto.request.user.UserRequest;
import com.focuz.administrationservice.application.mapper.request.userinfo.UserInfoRequestMapper;
import com.focuz.administrationservice.domain.entity.user.User;
import com.focuz.corestarter.domain.entity.template.mapper.DomainMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(
        config = GlobalMapStructConfiguration.class,
        uses = {UserInfoRequestMapper.class}
)
public interface UserRequestMapper extends DomainMapper<User, UserRequest> {
}
