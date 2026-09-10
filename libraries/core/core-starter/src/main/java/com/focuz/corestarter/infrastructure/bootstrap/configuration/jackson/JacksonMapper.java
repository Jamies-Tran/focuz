package com.focuz.corestarter.infrastructure.bootstrap.configuration.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.focuz.corestarter.domain.entity.exception.ApplicationException;
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

    public String convertToString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
