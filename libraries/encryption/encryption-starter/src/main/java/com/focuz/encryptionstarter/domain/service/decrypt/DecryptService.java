package com.focuz.encryptionstarter.domain.service.decrypt;

public interface DecryptService {
    String decrypt(String encodedData, String secretKey, String iv);
}
