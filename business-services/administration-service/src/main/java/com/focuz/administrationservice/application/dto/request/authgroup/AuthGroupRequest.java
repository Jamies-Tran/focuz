package com.focuz.administrationservice.application.dto.request.authgroup;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record AuthGroupRequest(
        String authGroupCode,
        @NotBlank(message = "Name không được bỏ trống")
        String authGroupName
) {
    public record AuthGroupListRequest(
            @Valid
            @NotNull(message = "Nhóm quyền không được bỏ trống")
            @Size(min = 1, message = "Danh sách nhóm quyền phải có ít nhất một phần tử")
            List<AuthGroupRequest> requestList
    ) {}

    public record AuthGroupCodeListRequest(
            @Size(min = 1, message = "Danh sách nhóm quyền phải có ít nhất một phần tử")
            List<String> authGroupCodeList
    ) {}
}
