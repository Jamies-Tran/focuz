package com.focuz.authstarter.infrastructure.bootstrap.configuration.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum JacksonMapper {
    INSTANCE;

    ObjectMapper objectMapper;

    JacksonMapper() {
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }
}
