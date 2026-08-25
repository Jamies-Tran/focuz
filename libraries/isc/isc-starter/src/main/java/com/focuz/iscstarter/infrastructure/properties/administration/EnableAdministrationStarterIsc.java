package com.focuz.iscstarter.infrastructure.properties.administration;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({AdministrationPropertyConfiguration.class})
public @interface EnableAdministrationStarterIsc {
}
