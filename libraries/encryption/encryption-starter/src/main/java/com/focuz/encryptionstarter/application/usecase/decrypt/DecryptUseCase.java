package com.focuz.encryptionstarter.application.usecase.decrypt;

import com.focuz.encryptionstarter.domain.service.decrypt.DecryptService;
import com.focuz.encryptionstarter.infrastructure.bootstrap.utils.KeyGeneratorUtil;
import com.focuz.encryptionstarter.infrastructure.properties.EncryptionStarterProperties;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DecryptUseCase implements DecryptService {
    EncryptionStarterProperties properties;
    KeyGeneratorUtil keyGeneratorUtil;

    @Override
    public String decrypt(String encodedData, String secretKey, String iv) {
        try {
            byte[] decodedIv = Base64.getDecoder().decode(iv.getBytes(StandardCharsets.UTF_8));
            SecretKey reverseSecretKey = keyGeneratorUtil.reverseAesKey(secretKey);
            byte[] decodedData = Base64.getDecoder().decode(encodedData.getBytes(StandardCharsets.UTF_8));
            int dataLength = decodedData.length - decodedIv.length;
            byte[] encryptedData = new byte[dataLength];
            System.arraycopy(decodedData, decodedIv.length, encryptedData, 0, dataLength);
            AlgorithmParameterSpec algorithmParameterSpec = new GCMParameterSpec(properties.getGcmTagLength(), decodedIv);
            Cipher cipher = Cipher.getInstance(properties.getRsaAlg());
            cipher.init(Cipher.DECRYPT_MODE, reverseSecretKey, algorithmParameterSpec);
            return new String(cipher.doFinal(encryptedData),  StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }
}
