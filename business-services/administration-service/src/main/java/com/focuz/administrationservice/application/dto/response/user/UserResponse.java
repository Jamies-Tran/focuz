package com.focuz.administrationservice.application.dto.response.user;

import com.focuz.administrationservice.domain.entity.userinfo.UserInfo;
import java.time.LocalDateTime;

public record UserResponse(
        Long userId,
        String username,
        String password,
        UserInfo userInfo,
        String statusCode,
        String statusName,
        String search,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
