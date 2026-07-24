package com.focuz.corestarter.infrastructure.bootstrap.auditor;

import com.focuz.corestarter.domain.service.auditor.AuditorProvider;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BaseAuditorConfiguration implements AuditorAware<String> {
    AuditorProvider auditorProvider;

    @Override
    @NonNull
    public Optional<String> getCurrentAuditor() {
        return auditorProvider.auditor();
    }
}
