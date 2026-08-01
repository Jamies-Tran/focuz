package com.focuz.administrationservice.application.mapper.response.userinfo;

import com.focuz.administrationservice.application.dto.response.userinfo.UserInfoResponse;
import com.focuz.administrationservice.domain.entity.userinfo.UserInfo;
import com.focuz.corestarter.domain.entity.template.mapper.DomainMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config =  GlobalMapStructConfiguration.class)
public interface UserInfoResponseMapper extends DomainMapper<UserInfo, UserInfoResponse> {
}
