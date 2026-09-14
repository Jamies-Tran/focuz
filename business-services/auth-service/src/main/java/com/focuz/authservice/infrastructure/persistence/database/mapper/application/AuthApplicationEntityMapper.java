package com.focuz.authservice.infrastructure.persistence.database.mapper.application;

import com.focuz.authservice.domain.entity.application.AuthApplication;
import com.focuz.authservice.infrastructure.persistence.database.entity.application.AuthApplicationEntity;
import com.focuz.corestarter.domain.entity.template.mapper.EntityMapper;
import com.focuz.corestarter.infrastructure.bootstrap.mapper.GlobalMapStructConfiguration;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Mapper(config = GlobalMapStructConfiguration.class)
public interface AuthApplicationEntityMapper extends EntityMapper<AuthApplicationEntity, AuthApplication> {
}
