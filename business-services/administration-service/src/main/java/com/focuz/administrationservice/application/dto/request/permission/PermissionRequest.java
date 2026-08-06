package com.focuz.administrationservice.application.dto.request.permission;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record PermissionRequest(
        String permissionCode,
        @NotBlank(message = "Tên quyền không được bỏ trống")
        String permissionName
) {
    public record PermissionListRequest(
            @Valid
            @NotNull(message = "Danh sách quyền không được bỏ trống")
            @Size(min = 1, message = "Danh sách quyền có ít nhất một phần tử")
            List<PermissionRequest> permissionRequestList
    ) {}

    public record PermissionCodeListRequest(
            @NotNull(message = "Danh sách mã quyền không được bỏ trống")
            @Size(min = 1, message = "Danh sách mã quyền có ít nhất một phần tử")
            List<String> permissionCodeList
    ) {}
}
