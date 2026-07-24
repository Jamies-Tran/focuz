package com.focuz.corestarter.domain.service.auditor;

import java.util.Optional;

public interface AuditorProvider {
    Optional<String> auditor();
}
