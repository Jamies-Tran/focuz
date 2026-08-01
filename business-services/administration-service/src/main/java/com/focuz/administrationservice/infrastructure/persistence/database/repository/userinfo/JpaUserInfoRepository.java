package com.focuz.administrationservice.infrastructure.persistence.database.repository.userinfo;

import com.focuz.administrationservice.infrastructure.persistence.database.entity.userinfo.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaUserInfoRepository extends JpaRepository<UserInfoEntity, Long> {
    Optional<UserInfoEntity> findByUserId(Long userId);
    List<UserInfoEntity> findAllByUserIdIn(List<Long> userIds);
    Boolean existsByPhone(String phone);
    Boolean existsByMail(String mail);
}
