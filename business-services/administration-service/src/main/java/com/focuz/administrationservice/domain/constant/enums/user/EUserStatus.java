package com.focuz.administrationservice.domain.constant.enums.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EUserStatus {
    ACTIVE("ACTIVE", "Kích hoạt"),
    INACTIVE("INACTIVE", "Vô hiệu hoá");

    String code;
    String name;
}
