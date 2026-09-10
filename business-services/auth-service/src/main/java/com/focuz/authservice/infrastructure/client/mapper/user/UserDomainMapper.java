package com.focuz.authservice.infrastructure.client.mapper.user;

import com.focuz.authservice.domain.entity.user.User;
import com.focuz.corestarter.domain.entity.template.mapper.DomainMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(
        config = GlobalMapStructConfiguration.class,
        implementationName = "AuthUserDomainMapper"
)
public interface UserDomainMapper extends DomainMapper<User, com.focuz.iscstarter.domain.entity.administration.user.User> {
}
