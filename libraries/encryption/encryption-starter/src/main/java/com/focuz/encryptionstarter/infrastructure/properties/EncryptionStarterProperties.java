package com.focuz.encryptionstarter.infrastructure.properties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "encryption")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EncryptionStarterProperties {
    Integer aesKeySize = 256;
    Integer gcmTagLength = 128;
    Integer aesIvSize = 12;
    String aesAlg = "AES/GCM/NoPadding";
    String rsaAlg = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";
    String pubKey;
    String priKey;
}
