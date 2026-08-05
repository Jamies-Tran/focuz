package com.focuz.administrationservice.domain.entity.authgroup;

import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;

@Builder
public record AuthGroup(
        Long authGroupId,
        String authGroupCode,
        String authGroupName,
        @With String statusCode,
        @With String statusName,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
