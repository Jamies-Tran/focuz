package com.focuz.administrationservice.domain.service.userinfo;

import com.focuz.administrationservice.domain.entity.userinfo.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserInfoService {
    UserInfo create(UserInfo userInfo);
    Optional<UserInfo> getDetailByUserId(Long userId);
    List<UserInfo> getListByUserIdIn(List<Long> userIds);
    UserInfo updateByUserId(Long userId, UserInfo userInfo);
    void removeByUserId(Long userId);
}
