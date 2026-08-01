package com.focuz.administrationservice.application.dto.request.user;

import com.focuz.administrationservice.application.dto.request.userinfo.UserInfoRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
        @NotBlank(message = "username không được bỏ trống")
        String username,
        @NotBlank(message = "password không được bỏ trống")
        String password,
        @Valid
        @NotNull
        UserInfoRequest userInfo
) {
}
