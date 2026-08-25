package com.focuz.iscstarter.domain.service.administration.user;

import com.focuz.iscstarter.domain.entity.administration.user.User;

import java.util.Optional;

public interface UserService {
    Optional<User> validateByUsernameAndPassword(String username, String password);
}
