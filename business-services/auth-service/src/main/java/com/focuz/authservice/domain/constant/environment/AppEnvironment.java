package com.focuz.authservice.domain.constant.environment;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppEnvironment {
    @Getter
    static Long authenticationCodeTtl;

    @Value("${environment.authenticationCode.ttl:300000}")
    public void setAuthenticationCodeTtl(Long authenticationCodeTtl) {
        AppEnvironment.authenticationCodeTtl = authenticationCodeTtl;
    }
}
