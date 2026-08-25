package com.focuz.encryptionstarter.infrastructure.bootstrap.utils;

import com.focuz.encryptionstarter.infrastructure.properties.EncryptionStarterProperties;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KeyGeneratorUtil {
    EncryptionStarterProperties properties;

    @PostConstruct
    public void init() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(properties.getRsaAlg());
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            log.info("Rsa public key:\n%s".formatted(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded())));
            log.info("Rsa private key:\n%s".formatted(Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded())));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateIv() {
        byte[] iv = new byte[properties.getAesIvSize()];
        new SecureRandom().nextBytes(iv);
        return Base64.getEncoder().encodeToString(iv);
    }

    public String generateRsaPubKey(String pubKey) {
        try {
            byte[] decodedPubKey = Base64.getDecoder().decode(pubKey.getBytes(StandardCharsets.UTF_8));
            X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(decodedPubKey);
            KeyFactory keyFactory = KeyFactory.getInstance(properties.getRsaAlg());
            return Base64.getEncoder().encodeToString(keyFactory.generatePublic(encodedKeySpec).getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public PublicKey reverseRsaPublicKey(String encodedPubKey) {
        try {
            byte[] decodedPubKey = Base64.getDecoder().decode(encodedPubKey.getBytes(StandardCharsets.UTF_8));
            X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(decodedPubKey);
            KeyFactory keyFactory = KeyFactory.getInstance(properties.getRsaAlg());
            return keyFactory.generatePublic(encodedKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateRsaPrivateKey(String priKey) {
        try {
            byte[] decodedPriKey = Base64.getDecoder().decode(priKey.getBytes(StandardCharsets.UTF_8));
            PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(decodedPriKey);
            KeyFactory keyFactory = KeyFactory.getInstance(properties.getRsaAlg());
            return Base64.getEncoder().encodeToString(keyFactory.generatePrivate(encodedKeySpec).getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public PrivateKey reverseRsaPrivateKey(String encodedPriKey) {
        try {
            byte[] decodedPriKey = Base64.getDecoder().decode(encodedPriKey.getBytes(StandardCharsets.UTF_8));
            PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(decodedPriKey);
            KeyFactory keyFactory = KeyFactory.getInstance(properties.getRsaAlg());
            return keyFactory.generatePrivate(encodedKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public SecretKey generateAesPriKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(properties.getAesAlg());
            keyGenerator.init(properties.getAesKeySize());
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException  e) {
            throw new RuntimeException(e);
        }
    }


    public String encryptSecretKey(SecretKey secretKey) {
        try {
            PublicKey publicKey = reverseRsaPublicKey(properties.getPubKey());
            Cipher cipher = Cipher.getInstance(properties.getRsaAlg());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(secretKey.getEncoded()));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public SecretKey reverseAesKey(String encodedAesKey) {
        try {
            byte[] decodedAesKey = Base64.getDecoder().decode(encodedAesKey);
            PrivateKey privateKey = reverseRsaPrivateKey(properties.getPriKey());
            Cipher cipher = Cipher.getInstance(properties.getRsaAlg());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new SecretKeySpec(cipher.doFinal(decodedAesKey), properties.getRsaAlg());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }
}
