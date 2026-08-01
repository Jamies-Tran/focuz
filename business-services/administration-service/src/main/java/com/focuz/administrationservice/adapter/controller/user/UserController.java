package com.focuz.administrationservice.adapter.controller.user;

import com.focuz.administrationservice.adapter.api.user.UserApi;
import com.focuz.administrationservice.application.dto.request.user.UserRequest;
import com.focuz.administrationservice.application.dto.response.user.UserResponse;
import com.focuz.administrationservice.application.mapper.request.user.UserRequestMapper;
import com.focuz.administrationservice.application.mapper.response.user.UserResponseMapper;
import com.focuz.administrationservice.domain.constant.enums.error.EAppError;
import com.focuz.administrationservice.domain.entity.user.UserCriteria;
import com.focuz.administrationservice.domain.service.user.UserService;
import com.focuz.corestarter.domain.entity.exception.ApplicationException;
import com.focuz.corestarter.domain.entity.template.response.PageResponse;
import com.focuz.corestarter.domain.entity.template.response.ValueResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController implements UserApi {
    UserService userService;
    UserRequestMapper requestMapper;
    UserResponseMapper responseMapper;

    @Override
    public ValueResponse<?> create(UserRequest request) {
        return ValueResponse.success(responseMapper
                .toDto(userService.create(requestMapper.toDomain(request))));
    }

    @Override
    public ValueResponse<UserResponse> getDetail(Long userId) {
        UserResponse response = userService.getDetail(userId)
                .map(responseMapper::toDto)
                .orElseThrow(() -> new ApplicationException(EAppError.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
        return ValueResponse.success(response);
    }

    @Override
    public PageResponse<UserResponse> getPage(
            String search,
            List<LocalDate> dobRange,
            List<LocalDateTime> createdAtRange,
            String sorter,
            Integer current,
            Integer pageSize
    ) {
        UserCriteria criteria = UserCriteria.builder()
                .search(search)
                .dobRange(dobRange)
                .createdAtRange(createdAtRange)
                .sorter(sorter)
                .current(current)
                .pageSize(pageSize)
                .build();
        return PageResponse.success(userService.getPage(criteria).map(responseMapper::toDto));
    }

    @Override
    public ValueResponse<?> update(Long userId, UserRequest request) {
        return ValueResponse.success(responseMapper.toDto(userService.update(userId, requestMapper.toDomain(request))));
    }

    @Override
    public ValueResponse<?> remove(Long userId) {
        userService.remove(userId);
        return ValueResponse.success(userId);
    }

    @Override
    public ValueResponse<?> active(Long userId) {
        return ValueResponse.success(responseMapper.toDto(userService.active(userId)));
    }

    @Override
    public ValueResponse<?> inactive(Long userId) {
        return ValueResponse.success(responseMapper.toDto(userService.inactive(userId)));
    }
}
