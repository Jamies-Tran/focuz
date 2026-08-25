package com.focuz.iscstarter.domain.constant.enums.error;

import com.focuz.corestarter.domain.entity.exception.ApplicationErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EAppError implements ApplicationErrorCode {
    CLIENT_ERROR("CLIENT_ERROR", "Lỗi client"),;

    String code;
    String message;
}
