package com.focuz.administrationservice.application.mapper.request.userinfo;

import com.focuz.administrationservice.application.dto.request.userinfo.UserInfoRequest;
import com.focuz.administrationservice.domain.entity.userinfo.UserInfo;
import com.focuz.corestarter.domain.entity.template.mapper.DomainMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config =  GlobalMapStructConfiguration.class)
public interface UserInfoRequestMapper extends DomainMapper<UserInfo, UserInfoRequest> {
}
