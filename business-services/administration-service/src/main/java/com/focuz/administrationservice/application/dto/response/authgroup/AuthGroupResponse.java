package com.focuz.administrationservice.application.dto.response.authgroup;

import java.time.LocalDateTime;

public record AuthGroupResponse(
        Long authGroupId,
        String authGroupCode,
        String authGroupName,
        String statusCode,
        String statusName,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
