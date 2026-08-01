package com.focuz.administrationservice.domain.entity.userinfo;

import com.focuz.administrationservice.infrastructure.bootstrap.utils.StringConvertUtils;
import lombok.Builder;
import lombok.With;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record UserInfo(
        Long userInfoId,
        @With Long userId,
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
    public UserInfo {
        fullName = "%s %s".formatted(firstName, lastName);
    }
}
