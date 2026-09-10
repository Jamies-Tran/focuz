package com.focuz.authservice.domain.entity.authentication.code;

import com.focuz.authservice.domain.entity.user.User;
import lombok.Builder;

@Builder
public record AuthenticationCode(
        String authenticationCode,
        User user,
        Long ttl
) {
}
