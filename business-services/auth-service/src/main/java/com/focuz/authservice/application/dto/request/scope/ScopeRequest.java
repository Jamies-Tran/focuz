package com.focuz.authservice.application.dto.request.scope;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ScopeRequest(
        @NotBlank(message = "Vui lòng nhập code")
        String scopeCode,
        @NotBlank(message = "Vui lòng nhập name")
        String scopeName
) {
    public record ScopeListRequest(
            @Valid
            List<ScopeRequest> scopes
    ) {}

    public record ScopeCodeListRequest(
            @NotNull(message = "Danh sách scope không được bỏ trống")
            @Size(min = 1, message = "Danh sách scope phải có ít nhất một phần tử")
            List<String> scopeCodes
    ) {}
}
