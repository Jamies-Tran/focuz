package com.focuz.authservice.domain.constant.enums.application;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EAuthApplicationStatus {
    ACTIVE("ACTIVE", "Kích hoạt"),
    INACTIVE("INACTIVE", "Chưa kích hoạt");

    String code;
    String name;
}
