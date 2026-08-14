package com.focuz.administrationservice.application.dto.request.client;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ClientRequest(
        String clientCode,
        @NotBlank(message = "Tên client không được bỏ trống")
        String clientName
) {
    public record ClientListRequest(
            @Valid
            @NotNull(message = "Danh sách client không được bỏ trống")
            @Size(min = 1, message = "Danh sách client có ít nhất một client")
            List<ClientRequest> clientList
    ) {}

    public record ClientCodeListRequest(
            @NotNull(message = "Danh sách mã client không được bỏ trống")
            @Size(min = 1, message = "Danh sách mã client có ít nhất một client")
            List<String> clientCodeList
    ) {}
}
