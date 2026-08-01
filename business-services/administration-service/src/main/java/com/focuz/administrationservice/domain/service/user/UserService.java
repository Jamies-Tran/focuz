package com.focuz.administrationservice.domain.service.user;

import com.focuz.administrationservice.domain.entity.user.User;
import com.focuz.administrationservice.domain.entity.user.UserCriteria;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {
    User create(User user);
    Optional<User> getDetail(Long userId);
    Page<User> getPage(UserCriteria criteria);
    User update(Long userId, User user);
    void remove(Long userId);
    User active(Long userId);
    User inactive(Long userId);
}
