package com.focuz.administrationservice.application.dto.request.user;

import com.focuz.administrationservice.application.dto.request.userinfo.UserInfoRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UserRequest(
        @NotBlank(message = "username không được bỏ trống")
        String username,
        @NotBlank(message = "password không được bỏ trống")
        String password,
        @Valid
        @NotNull
        UserInfoRequest userInfo
) {
        public record UserIdListRequest(
                @NotNull(message = "Danh sách user không được bỏ trống")
                @Size(min = 1, message = "Danh sách user phải có ít nhất một user")
                List<Long> userIdList
        ){}
}
