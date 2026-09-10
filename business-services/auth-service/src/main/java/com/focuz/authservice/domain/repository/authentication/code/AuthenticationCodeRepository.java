package com.focuz.authservice.domain.repository.authentication.code;

import com.focuz.authservice.domain.entity.authentication.code.AuthenticationCode;

import java.util.Optional;

public interface AuthenticationCodeRepository {
    AuthenticationCode save(AuthenticationCode authenticationCode);
    Optional<AuthenticationCode> findByAuthenticationCode(String authenticationCode);
}
