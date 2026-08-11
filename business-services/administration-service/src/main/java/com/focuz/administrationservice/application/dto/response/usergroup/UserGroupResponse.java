package com.focuz.administrationservice.application.dto.response.usergroup;

public record UserGroupResponse(
        Long userGroupId,
        Long authGroupId,
        Long userId,
        String username,
        String avatar,
        String mail,
        String phone
) {
}
