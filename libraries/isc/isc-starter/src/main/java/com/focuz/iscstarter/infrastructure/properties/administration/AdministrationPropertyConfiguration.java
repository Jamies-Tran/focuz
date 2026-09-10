package com.focuz.iscstarter.infrastructure.properties.administration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-administration-isc.properties")
@ComponentScan(
        basePackages = {
                "com.focuz.iscstarter.application.usecase.administration",
                "com.focuz.iscstarter.domain.entity.administration",
                "com.focuz.iscstarter.domain.service.administration",
                "com.focuz.iscstarter.infrastructure.client"
        }
)
@EnableFeignClients(
        basePackages = {
                "com.focuz.iscstarter.infrastructure.client.administration"
        }
)
public class AdministrationPropertyConfiguration {
}
