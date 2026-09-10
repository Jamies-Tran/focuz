package com.focuz.authservice.application.usecase.oauth;

import com.focuz.authservice.domain.constant.environment.AppEnvironment;
import com.focuz.authservice.domain.entity.authentication.code.AuthenticationCode;
import com.focuz.authservice.domain.repository.authentication.code.AuthenticationCodeRepository;
import com.focuz.authservice.domain.service.oauth.OauthService;
import com.focuz.authservice.infrastructure.client.mapper.user.UserDomainMapper;
import com.focuz.corestarter.infrastructure.bootstrap.configuration.jackson.JacksonMapper;
import com.focuz.corestarter.infrastructure.bootstrap.utils.StringConvertUtils;
import com.focuz.encryptionstarter.domain.service.encrypt.EncryptService;
import com.focuz.iscstarter.domain.entity.administration.user.User;
import com.focuz.iscstarter.domain.service.administration.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OauthUseCase implements OauthService {
    AuthenticationCodeRepository authenticationCodeRepository;
    UserDomainMapper userDomainMapper;
    UserService userService;
    EncryptService encryptService;

    @Override
    @Transactional
    public String getAuthenticationCode(String username, String password) {
        User user = userService.validateByUsernameAndPassword(username, password)
                .orElseThrow();
        String authenticationCode = generateAuthenticationCode(user);
        AuthenticationCode authCode = AuthenticationCode.builder()
                .authenticationCode(authenticationCode)
                .user(userDomainMapper.toDomain(user))
                .ttl(AppEnvironment.getAuthenticationCodeTtl())
                .build();
        authenticationCodeRepository.save(authCode);
        return authenticationCode;
    }

    private String generateAuthenticationCode(User user) {
        String userStr = JacksonMapper.INSTANCE.convertToString(user);
        String encryptedUser = encryptService.encrypt(userStr).encryptedData();
        return Base64.getUrlEncoder().encodeToString(encryptedUser.getBytes());
    }
}
