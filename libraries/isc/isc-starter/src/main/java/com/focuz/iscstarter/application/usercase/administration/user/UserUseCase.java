package com.focuz.iscstarter.application.usercase.administration.user;

import com.focuz.corestarter.domain.entity.exception.ApplicationException;
import com.focuz.corestarter.domain.entity.template.response.ValueResponse;
import com.focuz.iscstarter.domain.constant.enums.error.EAppError;
import com.focuz.iscstarter.domain.entity.administration.user.User;
import com.focuz.iscstarter.domain.service.administration.user.UserService;
import com.focuz.iscstarter.infrastructure.client.administration.AdministrationClient;
import com.focuz.iscstarter.infrastructure.client.administration.dto.request.user.UserValidateRequest;
import com.focuz.iscstarter.infrastructure.client.administration.dto.response.user.UserResponse;
import com.focuz.iscstarter.infrastructure.client.administration.mapper.response.UserDomainMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserUseCase implements UserService {
    AdministrationClient administrationClient;
    UserDomainMapper domainMapper;

    @Override
    public Optional<User> validateByUsernameAndPassword(String username, String password) {
        ValueResponse<UserResponse> userResponse = administrationClient.validate(
                new UserValidateRequest(username, password)
        );
        if(Boolean.FALSE.equals(userResponse.success()) || Objects.isNull(userResponse.value())) {
            throw new ApplicationException(EAppError.CLIENT_ERROR, userResponse.message(), HttpStatus.valueOf(userResponse.httpStatus()));
        }
        return Optional.of(
                domainMapper.toDomain(userResponse.value())
        );
    }
}
