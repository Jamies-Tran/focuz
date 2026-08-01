package com.focuz.administrationservice.domain.constant.enums.error;

import com.focuz.corestarter.domain.entity.exception.ApplicationErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EAppError implements ApplicationErrorCode {
    USER_INFO_NOT_FOUND("USER_INFO_NOT_FOUND", "User không tồn tại."),
    USER_NOT_FOUND("USER_NOT_FOUND", "User không tồn tại."),
    USER_INFO_DUPLICATED("USER_INFO_DUPLICATED", "Thông tin user đã tồn tại"),
    USER_DUPLICATED("USER_DUPLICATED", "Thông tin user đã tồn tại");

    String code;
    String message;
}
