package com.focuz.administrationservice.application.mapper.response.user;

import com.focuz.administrationservice.application.dto.response.user.UserResponse;
import com.focuz.administrationservice.application.mapper.response.userinfo.UserInfoResponseMapper;
import com.focuz.administrationservice.domain.entity.user.User;
import com.focuz.corestarter.domain.entity.template.mapper.DomainMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(
        config = GlobalMapStructConfiguration.class,
        uses = {UserInfoResponseMapper.class}
)
public interface UserResponseMapper extends DomainMapper<User, UserResponse> {
}
