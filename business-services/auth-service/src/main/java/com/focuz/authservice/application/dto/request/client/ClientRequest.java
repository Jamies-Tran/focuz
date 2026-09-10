package com.focuz.authservice.application.dto.request.client;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ClientRequest(
        @NotBlank(message = "Mã client không được bỏ trống")
        String clientCode,
        @NotBlank(message = "redirect url không được bỏ trống")
        String redirectUri
) {
    public record ClientListRequest(
            @Valid
            List<ClientRequest> clients
    ) {}

    public record ClientCodeListRequest(
            @NotNull(message = "Danh sách mã không được bỏ trống")
            @Size(min = 1, message = "Danh sách mã phải có ít nhất 1 phần tử")
            List<String> clientCodes
    ) {
    }
}
