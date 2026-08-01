package com.focuz.administrationservice.infrastructure.persistence.database.mapper.user;

import com.focuz.administrationservice.domain.entity.user.User;
import com.focuz.administrationservice.infrastructure.persistence.database.entity.user.UserEntity;
import com.focuz.corestarter.domain.entity.template.mapper.EntityMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface UserEntityMapper extends EntityMapper<UserEntity, User> {
}
