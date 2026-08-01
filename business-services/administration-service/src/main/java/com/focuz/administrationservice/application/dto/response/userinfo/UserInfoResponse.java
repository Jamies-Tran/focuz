package com.focuz.administrationservice.application.dto.response.userinfo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserInfoResponse(
        Long userInfoId,
        Long userId,
        String firstName,
        String lastName,
        String fullName,
        String phone,
        String mail,
        String address,
        LocalDate dob,
        String search,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
