package com.focuz.administrationservice.application.dto.request.user;

import jakarta.validation.constraints.NotBlank;

public record UserValidateRequest(
        @NotBlank(message = "username không được bỏ trống")
        String username,
        @NotBlank(message = "password không được bỏ trống")
        String password
) {
}
