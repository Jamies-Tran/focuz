package com.focuz.authservice.application.dto.request.authentication.code;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationCodeRequest(
        @NotBlank(message = "Vui lòng nhập username")
        String username,
        @NotBlank(message = "Vui lòng nhập password")
        String password
) {
}
