package com.focuz.authservice.infrastructure.persistence.cache.repository.authentication.code;

import com.focuz.authservice.infrastructure.persistence.cache.entity.authentication.code.AuthenticationCodeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CacheAuthenticationRepository extends CrudRepository<AuthenticationCodeEntity, String> {
    Optional<AuthenticationCodeEntity> findByAuthenticationCode(String authenticationCode);
}
