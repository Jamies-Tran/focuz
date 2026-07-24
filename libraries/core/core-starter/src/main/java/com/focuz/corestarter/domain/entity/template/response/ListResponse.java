package com.focuz.corestarter.domain.entity.template.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ListResponse<T>(
        List<T> data,
        Boolean success,
        String message,
        String errorCode,
        String httpStatus,
        LocalDateTime responseAt,
        Metadata metadata
) {
    @Builder
    public record Metadata(
            Integer size
    ) {}

    public static <T> ListResponse<T> success(List<T> data) {
        return ListResponse.<T>builder()
                .data(data)
                .success(true)
                .message("Success")
                .responseAt(LocalDateTime.now())
                .httpStatus(String.valueOf(HttpStatus.OK.value()))
                .metadata(
                        Metadata.builder()
                                .size(data.size())
                                .build()
                )
                .build();
    }

    public static <T> ListResponse<T> fail(String message, String errorCode, HttpStatus httpStatus) {
        return ListResponse.<T>builder()
                .success(false)
                .message(message)
                .errorCode(errorCode)
                .httpStatus(String.valueOf(httpStatus.value()))
                .responseAt(LocalDateTime.now())
                .build();
    }
}
