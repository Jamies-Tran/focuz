package com.focuz.administrationservice.domain.entity.usergroup;

import lombok.Builder;

import java.util.List;

@Builder
public record UserGroup(
        Long userGroupId,
        Long userId,
        Long authGroupId,
        String username,
        String avatar,
        String mail,
        String phone
) {
    public static List<UserGroup> of(Long authGroupId, List<Long> userIds) {
        return userIds.stream()
                .map(userId -> UserGroup.builder()
                        .authGroupId(authGroupId)
                        .userId(userId)
                        .build())
                .toList();
    }
}
