package com.focuz.administrationservice.domain.repository.userinfo;

import com.focuz.administrationservice.domain.entity.userinfo.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository {
    UserInfo save(UserInfo userInfo);
    Optional<UserInfo> findByUserId(Long userId);
    Optional<UserInfo> updateByUserId(Long userId, UserInfo userInfo);
    List<UserInfo> findAllByUserIdIn(List<Long> userIds);
    void deleteByUserId(Long userId);
    Boolean existsByPhone(String phone);
    Boolean existsByMail(String mail);
}
