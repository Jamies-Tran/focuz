package com.focuz.authservice.infrastructure.persistence.cache.mapper.authentication.code;

import com.focuz.authservice.domain.entity.authentication.code.AuthenticationCode;
import com.focuz.authservice.infrastructure.persistence.cache.entity.authentication.code.AuthenticationCodeEntity;
import com.focuz.authservice.infrastructure.persistence.cache.mapper.user.UserEntityMapper;
import com.focuz.corestarter.domain.entity.template.mapper.EntityMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(
        config = GlobalMapStructConfiguration.class,
        uses = {
                UserEntityMapper.class
        }
)
public interface AuthenticationCodeEntityMapper extends EntityMapper<AuthenticationCodeEntity, AuthenticationCode> {
}
