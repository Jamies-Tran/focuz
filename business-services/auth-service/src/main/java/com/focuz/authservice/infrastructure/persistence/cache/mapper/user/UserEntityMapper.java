package com.focuz.authservice.infrastructure.persistence.cache.mapper.user;

import com.focuz.authservice.domain.entity.user.User;
import com.focuz.authservice.infrastructure.persistence.cache.entity.user.UserEntity;
import com.focuz.corestarter.domain.entity.template.mapper.EntityMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface UserEntityMapper extends EntityMapper<UserEntity, User> {
}
