package com.focuz.administrationservice.application.usecase.user;

import com.focuz.administrationservice.domain.constant.enums.error.EAppError;
import com.focuz.administrationservice.domain.constant.enums.user.EUserStatus;
import com.focuz.administrationservice.domain.entity.user.User;
import com.focuz.administrationservice.domain.entity.user.UserCriteria;
import com.focuz.administrationservice.domain.entity.userinfo.UserInfo;
import com.focuz.administrationservice.domain.repository.user.UserRepository;
import com.focuz.administrationservice.domain.service.user.UserService;
import com.focuz.administrationservice.domain.service.userinfo.UserInfoService;
import com.focuz.corestarter.domain.entity.exception.ApplicationException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserUseCase implements UserService {
    UserRepository repository;
    UserInfoService userInfoService;
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User create(User user) {
        validateCreate(user);
        user = user.withPassword(passwordEncoder.encode(user.password()));
        User savedUser = repository.save(user);
        UserInfo userInfo = userInfoService.create(user.updateUserInfoUserId(savedUser.userId()).userInfo());
        return savedUser.withUserInfo(userInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getDetail(Long userId) {
        UserInfo userInfo = userInfoService.getDetailByUserId(userId)
                .orElseThrow(() -> new ApplicationException(EAppError.USER_INFO_NOT_FOUND, HttpStatus.NOT_FOUND));
        return repository.findById(userId)
                .map(u -> u.withUserInfo(userInfo));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> getPage(UserCriteria criteria) {
        Page<User> users = repository.findAll(criteria, criteria.pageRequest());
        List<Long> userIds = users.stream()
                .map(User::userId)
                .toList();
        Map<Long, UserInfo> userInfos = userInfoService.getListByUserIdIn(userIds)
                .stream()
                .collect(Collectors.toMap(UserInfo::userId, Function.identity()));
        return users
                .map(u -> u.withUserInfo(userInfos.getOrDefault(u.userId(), null)));
    }

    @Override
    @Transactional
    public User update(Long userId, User user) {
        validateUpdate(userId, user);
        UserInfo userInfo = userInfoService.updateByUserId(userId, user.userInfo());
        return repository.update(userId, user)
                .map(u -> u.withUserInfo(userInfo))
                .orElseThrow(() -> new ApplicationException(EAppError.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public void remove(Long userId) {
        userInfoService.removeByUserId(userId);
        repository.delete(userId);
    }

    @Override
    @Transactional
    public User active(Long userId) {
        return repository.update(userId, EUserStatus.ACTIVE)
                .orElseThrow(() -> new ApplicationException(EAppError.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public User inactive(Long userId) {
        return repository.update(userId, EUserStatus.INACTIVE)
                .orElseThrow(() -> new ApplicationException(EAppError.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    private void validateCreate(User user) {
        if(repository.existsByUsername(user.username())) {
            String message = String.format("Username %s đã tồn tại", user.username());
            throw new ApplicationException(EAppError.USER_DUPLICATED,  message, HttpStatus.BAD_REQUEST);
        }
    }

    private void validateUpdate(Long userId, User user) {
        repository.findById(userId).ifPresent(u -> {
            if(!Objects.equals(u.username(), user.username())) {
                if(repository.existsByUsername(user.username())) {
                    String message = String.format("Username %s đã tồn tại", user.username());
                    throw new ApplicationException(EAppError.USER_DUPLICATED,  message, HttpStatus.BAD_REQUEST);
                }
            }
        });
    }
}
