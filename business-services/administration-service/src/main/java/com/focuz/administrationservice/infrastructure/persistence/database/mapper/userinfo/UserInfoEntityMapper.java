package com.focuz.administrationservice.infrastructure.persistence.database.mapper.userinfo;

import com.focuz.administrationservice.domain.entity.userinfo.UserInfo;
import com.focuz.administrationservice.infrastructure.persistence.database.entity.userinfo.UserInfoEntity;
import com.focuz.corestarter.domain.entity.template.mapper.EntityMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface UserInfoEntityMapper extends EntityMapper<UserInfoEntity, UserInfo> {
}
