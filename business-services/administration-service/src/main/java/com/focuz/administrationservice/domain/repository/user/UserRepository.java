package com.focuz.administrationservice.domain.repository.user;

import com.focuz.administrationservice.domain.constant.enums.user.EUserStatus;
import com.focuz.administrationservice.domain.entity.user.User;
import com.focuz.administrationservice.domain.entity.user.UserCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long userId);
    Page<User> findAll(UserCriteria criteria, PageRequest pageRequest);
    Optional<User> update(Long userId, User user);
    Optional<User> update(Long userId, EUserStatus status);
    void delete(Long userId);
    Boolean existsByUsername(String username);
}
