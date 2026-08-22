package com.focuz.encryptionstarter.domain.service.encrypt;

import com.focuz.encryptionstarter.domain.entity.encrypt.Encrypt;

public interface EncryptService {
    Encrypt encrypt(String plainData);
}
