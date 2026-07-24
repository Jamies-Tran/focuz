package com.focuz.corestarter.domain.entity.template.mapper;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface DomainMapper<Domain, Dto> {
    Domain toDomain(Dto dto);
    Dto toDto(Domain domain);
    List<Domain> toDomain(List<Dto> dtoList);
    List<Dto> toDto(List<Domain> domainList);
}
