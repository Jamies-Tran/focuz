package com.focuz.encryptionstarter;

import com.focuz.encryptionstarter.application.usecase.decrypt.DecryptUseCase;
import com.focuz.encryptionstarter.application.usecase.encrypt.EncryptUseCase;
import com.focuz.encryptionstarter.domain.service.decrypt.DecryptService;
import com.focuz.encryptionstarter.domain.service.encrypt.EncryptService;
import com.focuz.encryptionstarter.infrastructure.bootstrap.utils.KeyGeneratorUtil;
import com.focuz.encryptionstarter.infrastructure.properties.EncryptionStarterProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(EncryptionStarterProperties.class)
public class EncryptionStarterAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public KeyGeneratorUtil keyGeneratorUtil(EncryptionStarterProperties properties) {
        return new KeyGeneratorUtil(properties);
    }

    @Bean
    @ConditionalOnMissingBean
    public EncryptService encryptService(
            EncryptionStarterProperties properties,
            KeyGeneratorUtil keyGeneratorUtil
    ) {
        return new EncryptUseCase(properties, keyGeneratorUtil);
    }


    @Bean
    @ConditionalOnMissingBean
    public DecryptService decryptService(
            EncryptionStarterProperties properties,
            KeyGeneratorUtil keyGeneratorUtil
    ) {
        return new DecryptUseCase(properties, keyGeneratorUtil);
    }
}
