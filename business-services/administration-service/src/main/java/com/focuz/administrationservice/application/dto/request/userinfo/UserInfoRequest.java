package com.focuz.administrationservice.application.dto.request.userinfo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record UserInfoRequest(
        @NotBlank(message = "Tên không được bỏ trống")
        String firstName,
        @NotBlank(message = "Họ không được bỏ trống")
        String lastName,
        String phone,
        @NotBlank(message = "Mail không được bỏ trống")
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Mail không hợp lệ")
        String mail,
        String address,
        LocalDate dob
) {
}
