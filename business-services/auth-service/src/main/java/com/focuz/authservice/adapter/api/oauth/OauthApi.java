package com.focuz.authservice.adapter.api.oauth;

import com.focuz.authservice.application.dto.request.authentication.code.AuthenticationCodeRequest;
import com.focuz.authservice.application.dto.response.authentication.code.AuthenticationCodeResponse;
import com.focuz.corestarter.domain.entity.template.response.ValueResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/oauth")
public interface OauthApi {
    @PostMapping("/authenticationCode")
    ValueResponse<AuthenticationCodeResponse> getAuthenticationCode(@RequestBody @Validated AuthenticationCodeRequest request);
}
