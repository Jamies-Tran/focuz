package com.focuz.authservice.domain.service.oauth;

public interface OauthService {
    String getAuthenticationCode(String username, String password);
}
