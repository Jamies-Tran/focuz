package com.focuz.administrationservice.infrastructure.persistence.database.adapter.userinfo;

import com.focuz.administrationservice.domain.entity.userinfo.UserInfo;
import com.focuz.administrationservice.domain.repository.userinfo.UserInfoRepository;
import com.focuz.administrationservice.infrastructure.persistence.database.mapper.userinfo.UserInfoEntityMapper;
import com.focuz.administrationservice.infrastructure.persistence.database.repository.userinfo.JpaUserInfoRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JpaUserInfoPersistenceAdapter implements UserInfoRepository {
    JpaUserInfoRepository repository;
    UserInfoEntityMapper mapper;

    @Override
    public UserInfo save(UserInfo userInfo) {
        return mapper.toDomain(repository.save(mapper.toEntity(userInfo)));
    }

    @Override
    public Optional<UserInfo> findByUserId(Long userId) {
        return repository.findByUserId(userId)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<UserInfo> updateByUserId(Long userId, UserInfo userInfo) {
        return repository.findByUserId(userId)
                .map(u -> {
                    mapper.update(u, userInfo);
                    return mapper.toDomain(repository.save(u));
                });
    }

    @Override
    public List<UserInfo> findAllByUserIdIn(List<Long> userIds) {
        return mapper.toDomain(repository.findAllByUserIdIn(userIds));
    }

    @Override
    public void deleteByUserId(Long userId) {
        repository.findByUserId(userId).ifPresent(repository::delete);
    }

    @Override
    public Boolean existsByPhone(String phone) {
        return repository.existsByPhone(phone);
    }

    @Override
    public Boolean existsByMail(String mail) {
        return repository.existsByMail(mail);
    }
}
