package com.focuz.corestarter.domain.entity.template.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record ValueResponse<T>(
        T value,
        Boolean success,
        String message,
        String errorCode,
        String httpStatus,
        LocalDateTime responseAt
) {
    public static <T> ValueResponse<T> success(T value) {
        return ValueResponse.<T>builder()
                .value(value)
                .success(true)
                .message("Success")
                .httpStatus(String.valueOf(HttpStatus.OK.value()))
                .responseAt(LocalDateTime.now())
                .build();
    }

    public static <T> ValueResponse<T> fail(String message, String errorCode, HttpStatus httpStatus) {
        return ValueResponse.<T>builder()
                .success(false)
                .message(message)
                .errorCode(errorCode)
                .httpStatus(String.valueOf(httpStatus.value()))
                .responseAt(LocalDateTime.now())
                .build();
    }
}
