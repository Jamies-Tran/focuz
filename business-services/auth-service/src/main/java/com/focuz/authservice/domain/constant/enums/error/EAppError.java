package com.focuz.authservice.domain.constant.enums.error;

import com.focuz.corestarter.domain.entity.exception.ApplicationErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EAppError implements ApplicationErrorCode {
    CLIENT_NOT_FOUND("CLIENT_NOT_FOUND", "Client không tồn tại"),
    SCOPE_NOT_FOUND("SCOPE_NOT_FOUND", "Scope không tồn tại"),
    SCOPE_DUPLICATED_IN_LIST("SCOPE_DUPLICATED_IN_LIST", "Danh sách scopes có sự trùng lặp"),
    SCOPE_DUPLICATED_IN_DB("SCOPE_DUPLICATED_IN_DB", "Scopes đã tồn tại");


    String code;
    String message;
}
