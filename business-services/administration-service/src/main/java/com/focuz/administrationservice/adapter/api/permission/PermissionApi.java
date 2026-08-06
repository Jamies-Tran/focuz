package com.focuz.administrationservice.adapter.api.permission;

import com.focuz.administrationservice.application.dto.request.permission.PermissionRequest;
import com.focuz.administrationservice.application.dto.response.permission.PermissionResponse;
import com.focuz.corestarter.domain.entity.template.response.ListResponse;
import com.focuz.corestarter.domain.entity.template.response.PageResponse;
import com.focuz.corestarter.domain.entity.template.response.ValueResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/permissions")
public interface PermissionApi {
    @PostMapping
    ListResponse<PermissionResponse> createList(@RequestBody @Validated PermissionRequest.PermissionListRequest request);

    @GetMapping("/{permissionCode}")
    ValueResponse<PermissionResponse> getDetailByCode(@PathVariable String permissionCode);

    @GetMapping
    PageResponse<PermissionResponse> getPage(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "permissionCodes", defaultValue = "")
            List<String> permissionCodes,
            @RequestParam(required = false, value = "statusCodes", defaultValue = "")
            List<String> statusCodes,
            @RequestParam(required = false, value = "sorter", defaultValue = "createdAt_desc")
            String sorter,
            @RequestParam(required = false, value = "current", defaultValue = "0")
            Integer current,
            @RequestParam(required = false, value = "pageSize", defaultValue = "25")
            Integer pageSize
    );

    @PutMapping("/{permissionCode}")
    ValueResponse<PermissionResponse> updateByCode(@PathVariable String permissionCode, @RequestBody @Validated PermissionRequest request);

    @PatchMapping("/{permissionCode}/active")
    ValueResponse<PermissionResponse> activeByCode(@PathVariable String permissionCode);

    @PatchMapping("/{permissionCode}/inactive")
    ValueResponse<PermissionResponse> inactiveByCode(@PathVariable String permissionCode);

    @DeleteMapping
    ListResponse<?> removeListByCodeIn(@RequestBody @Validated PermissionRequest.PermissionCodeListRequest request);
}
