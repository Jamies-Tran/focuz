package com.focuz.administrationservice.domain.entity.user;

import com.focuz.administrationservice.domain.constant.enums.user.EUserStatus;
import com.focuz.administrationservice.domain.entity.userinfo.UserInfo;
import com.focuz.administrationservice.infrastructure.bootstrap.utils.StringConvertUtils;
import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;
import java.util.Optional;

@Builder
public record User(
        Long userId,
        String username,
        @With String password,
        @With UserInfo userInfo,
        @With String statusCode,
        @With String statusName,
        String search,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public User updateUserInfoUserId(Long userId) {
        return this.withUserInfo(this.userInfo.withUserId(userId));
    }
}
