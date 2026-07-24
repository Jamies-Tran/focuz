package com.focuz.corestarter;

import com.focuz.corestarter.adapter.advice.GlobalRestControllerAdvice;
import com.focuz.corestarter.domain.service.auditor.AuditorProvider;
import com.focuz.corestarter.infrastructure.bootstrap.auditor.BaseAuditorConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.DateTimeProvider;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@AutoConfiguration
public class CoreStarterAutoconfiguration {
    @Bean
    @ConditionalOnMissingBean
    public AuditorProvider auditorProvider() {
        return  () -> Optional.of("System");
    }

    @Bean
    @ConditionalOnMissingBean
    public BaseAuditorConfiguration baseAuditorConfiguration(AuditorProvider auditorProvider) {
        return new BaseAuditorConfiguration(auditorProvider);
    }

    @Bean
    @ConditionalOnMissingBean
    public GlobalRestControllerAdvice globalRestControllerAdvice() {
        return new GlobalRestControllerAdvice();
    }

    @Bean
    @ConditionalOnMissingBean
    public DateTimeProvider dateTimeProvider() {
        return  () -> Optional.of(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));
    }
}
