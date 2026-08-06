package com.focuz.administrationservice.domain.entity.authgroup;

import com.focuz.administrationservice.domain.entity.permission.Permission;
import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record AuthGroup(
        Long authGroupId,
        String authGroupCode,
        String authGroupName,
        @With List<Permission> permissions,
        @With String statusCode,
        @With String statusName,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
