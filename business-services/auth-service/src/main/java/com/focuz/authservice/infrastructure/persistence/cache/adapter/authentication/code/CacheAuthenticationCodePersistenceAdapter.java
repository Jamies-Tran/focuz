package com.focuz.authservice.infrastructure.persistence.cache.adapter.authentication.code;

import com.focuz.authservice.domain.entity.authentication.code.AuthenticationCode;
import com.focuz.authservice.domain.repository.authentication.code.AuthenticationCodeRepository;
import com.focuz.authservice.infrastructure.persistence.cache.mapper.authentication.code.AuthenticationCodeEntityMapper;
import com.focuz.authservice.infrastructure.persistence.cache.repository.authentication.code.CacheAuthenticationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CacheAuthenticationCodePersistenceAdapter implements AuthenticationCodeRepository {
    CacheAuthenticationRepository repository;
    AuthenticationCodeEntityMapper mapper;

    @Override
    public AuthenticationCode save(AuthenticationCode authenticationCode) {
        return mapper.toDomain(
                repository.save(
                        mapper.toEntity(authenticationCode)
                )
        );
    }

    @Override
    public Optional<AuthenticationCode> findByAuthenticationCode(String authenticationCode) {
        return repository.findByAuthenticationCode(authenticationCode)
                .map(mapper::toDomain);
    }
}
