package com.focuz.encryptionstarter.domain.entity.encrypt;

import lombok.Builder;

@Builder
public record Encrypt(
        String encryptedData,
        String secretKey,
        String iv
) {
}
