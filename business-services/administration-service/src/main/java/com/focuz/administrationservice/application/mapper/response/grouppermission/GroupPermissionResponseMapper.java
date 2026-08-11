package com.focuz.administrationservice.application.mapper.response.grouppermission;

import com.focuz.administrationservice.application.dto.response.grouppermission.GroupPermissionResponse;
import com.focuz.administrationservice.domain.entity.grouppermission.GroupPermission;
import com.focuz.corestarter.domain.entity.template.mapper.DomainMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface GroupPermissionResponseMapper extends DomainMapper<GroupPermission, GroupPermissionResponse> {
}
