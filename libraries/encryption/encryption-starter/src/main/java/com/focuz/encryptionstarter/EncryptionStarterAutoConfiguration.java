package com.focuz.encryptionstarter;

import com.focuz.encryptionstarter.infrastructure.properties.EncryptionStarterProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@AutoConfiguration
@EnableConfigurationProperties(EncryptionStarterProperties.class)
public class EncryptionStarterAutoConfiguration {
}
