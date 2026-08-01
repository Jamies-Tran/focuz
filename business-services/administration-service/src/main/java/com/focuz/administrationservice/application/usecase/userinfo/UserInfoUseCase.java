package com.focuz.administrationservice.application.usecase.userinfo;

import com.focuz.administrationservice.domain.constant.enums.error.EAppError;
import com.focuz.administrationservice.domain.entity.userinfo.UserInfo;
import com.focuz.administrationservice.domain.repository.userinfo.UserInfoRepository;
import com.focuz.administrationservice.domain.service.userinfo.UserInfoService;
import com.focuz.corestarter.domain.entity.exception.ApplicationException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserInfoUseCase implements UserInfoService {
    UserInfoRepository repository;

    @Override
    @Transactional
    public UserInfo create(UserInfo userInfo) {
        validateCreate(userInfo);
        return repository.save(userInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserInfo> getDetailByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserInfo> getListByUserIdIn(List<Long> userIds) {
        return repository.findAllByUserIdIn(userIds);
    }

    @Override
    @Transactional
    public UserInfo updateByUserId(Long userId, UserInfo userInfo) {
        validateUpdate(userId, userInfo);
        return repository.updateByUserId(userId, userInfo)
                .orElseThrow(() -> new ApplicationException(EAppError.USER_INFO_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public void removeByUserId(Long userId) {
        repository.deleteByUserId(userId);
    }

    private void validateCreate(UserInfo userInfo) {
        if(repository.existsByPhone(userInfo.phone())) {
            String message = "Số điện thoại %s đã tồn tại".formatted(userInfo.phone());
            throw new ApplicationException(EAppError.USER_INFO_DUPLICATED, message, HttpStatus.BAD_REQUEST);
        }
        if(repository.existsByMail(userInfo.mail())) {
            String message = "Mail %s đã tồn tại".formatted(userInfo.mail());
            throw new ApplicationException(EAppError.USER_INFO_DUPLICATED, message, HttpStatus.BAD_REQUEST);
        }
    }

    private void validateUpdate(Long userId, UserInfo userInfo) {
        repository.findByUserId(userId)
                .ifPresent(u -> {
                    if(!Objects.equals(u.phone(), userInfo.phone())) {
                        if(repository.existsByPhone(userInfo.phone())) {
                            String message = "Số điện thoại %s đã tồn tại".formatted(userInfo.phone());
                            throw new ApplicationException(EAppError.USER_INFO_DUPLICATED, message, HttpStatus.BAD_REQUEST);
                        }
                    }
                    if(!Objects.equals(u.mail(), userInfo.mail())) {
                        if(repository.existsByMail(userInfo.mail())) {
                            String message = "Mail %s đã tồn tại".formatted(userInfo.mail());
                            throw new ApplicationException(EAppError.USER_INFO_DUPLICATED, message, HttpStatus.BAD_REQUEST);
                        }
                    }
                });
    }
}
