package com.focuz.corestarter.domain.entity.template.mapper;

public interface DaoMapper<Dao, Domain> {
    Dao toDomain(Domain domain);
}
