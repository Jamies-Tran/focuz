package com.focuz.iscstarter.infrastructure.client.administration.dto.request.user;

import jakarta.validation.constraints.NotBlank;

public record UserValidateRequest(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
