package com.focuz.authstarter.domain.service;

import com.focuz.authstarter.domain.entity.UserPrinciple;

import java.util.Optional;

public interface JwtProvider {
    String generateAccessToken(UserPrinciple userPrinciple);
    String generateRefreshToken(UserPrinciple userPrinciple);
    Optional<UserPrinciple> parseAccessToken(String accessToken);
    Optional<UserPrinciple> parseRefreshToken(String refreshToken);
}
