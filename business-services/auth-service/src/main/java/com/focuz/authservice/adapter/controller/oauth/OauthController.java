package com.focuz.authservice.adapter.controller.oauth;

import com.focuz.authservice.adapter.api.oauth.OauthApi;
import com.focuz.authservice.application.dto.request.authentication.code.AuthenticationCodeRequest;
import com.focuz.authservice.application.dto.response.authentication.code.AuthenticationCodeResponse;
import com.focuz.authservice.domain.service.oauth.OauthService;
import com.focuz.corestarter.domain.entity.template.response.ValueResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OauthController implements OauthApi {
    OauthService oauthService;

    @Override
    public ValueResponse<AuthenticationCodeResponse> getAuthenticationCode(AuthenticationCodeRequest request) {
        return ValueResponse.success(
                new AuthenticationCodeResponse(
                        oauthService.getAuthenticationCode(request.username(), request.password())
                )
        );
    }
}
