package com.focuz.authservice.application.dto.request.application;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record AuthApplicationRequest(
        @NotBlank(message = "Vui lòng nhập code")
        String applicationCode,
        @NotBlank(message = "Vui lòng nhập name")
        String applicationName
) {
    public record AuthApplicationListRequest(
            @Valid
            List<AuthApplicationRequest> applications
    ){}

    public record AuthApplicationCodeListRequest(
            @NotNull(message = "Danh sách code không được bỏ trống")
            @Size(min = 1, message = "Danh sách code phải có ít nhất 1 phần tử")
            List<String> applicationCodes
    ) {}
}
