package com.focuz.administrationservice.infrastructure.persistence.database.adapter.user;

import com.focuz.administrationservice.domain.constant.enums.user.EUserStatus;
import com.focuz.administrationservice.domain.entity.user.User;
import com.focuz.administrationservice.domain.entity.user.UserCriteria;
import com.focuz.administrationservice.domain.repository.user.UserRepository;
import com.focuz.administrationservice.infrastructure.persistence.database.mapper.user.UserEntityMapper;
import com.focuz.administrationservice.infrastructure.persistence.database.repository.user.JpaUserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JpaUserPersistenceAdapter implements UserRepository {
    JpaUserRepository repository;
    UserEntityMapper mapper;

    @Override
    public User save(User user) {
        return mapper.toDomain(repository.save(mapper.toEntity(user)));
    }

    @Override
    public Optional<User> findById(Long userId) {
        return repository.findById(userId)
                .map(mapper::toDomain);
    }

    @Override
    public Page<User> findAll(UserCriteria criteria, PageRequest pageRequest) {
        return repository.findAll(criteria, pageRequest)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<User> update(Long userId, User user) {
        return repository.findById(userId)
                .map(u -> {
                    mapper.update(u, user);
                    return mapper.toDomain(repository.save(u));
                });
    }

    @Override
    public Optional<User> update(Long userId, EUserStatus status) {
        return repository.findById(userId)
                .map(u -> {
                    u.setStatusCode(status.getCode());
                    u.setStatusName(status.getName());
                    return mapper.toDomain(repository.save(u));
                });
    }

    @Override
    public void delete(Long userId) {
        repository.deleteById(userId);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }
}
