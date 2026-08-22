package com.focuz.encryptionstarter.application.usecase.encrypt;

import com.focuz.encryptionstarter.domain.entity.encrypt.Encrypt;
import com.focuz.encryptionstarter.domain.service.encrypt.EncryptService;
import com.focuz.encryptionstarter.infrastructure.bootstrap.utils.KeyGeneratorUtil;
import com.focuz.encryptionstarter.infrastructure.properties.EncryptionStarterProperties;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EncryptUseCase implements EncryptService {
    EncryptionStarterProperties properties;
    KeyGeneratorUtil keyGeneratorUtil;

    @Override
    public Encrypt encrypt(String plainData) {
        try {
            String encodedIv = keyGeneratorUtil.generateIv();
            byte[] iv = Base64.getDecoder().decode(encodedIv);
            SecretKey secretKey = keyGeneratorUtil.generateAesPriKey();
            AlgorithmParameterSpec algorithmParameterSpec = new GCMParameterSpec(properties.getGcmTagLength(), iv);
            Cipher cipher = Cipher.getInstance(properties.getAesAlg());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, algorithmParameterSpec);
            byte[] encryptedData = cipher.doFinal(plainData.getBytes());
            byte[] encryptedIDataAndIv = new byte[iv.length + encryptedData.length];
            System.arraycopy(iv, 0, encryptedIDataAndIv, 0, iv.length);
            System.arraycopy(encryptedData, 0, encryptedIDataAndIv, iv.length, encryptedData.length);
            return Encrypt.builder()
                    .encryptedData(Base64.getEncoder().encodeToString(encryptedIDataAndIv))
                    .iv(encodedIv)
                    .secretKey(keyGeneratorUtil.encryptSecretKey(secretKey))
                    .build();
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException |
                 InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }
}
