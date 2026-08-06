package com.focuz.administrationservice.adapter.api.authgroup;

import com.focuz.administrationservice.application.dto.request.authgroup.AuthGroupRequest;
import com.focuz.administrationservice.application.dto.request.permission.PermissionRequest;
import com.focuz.administrationservice.application.dto.response.authgroup.AuthGroupResponse;
import com.focuz.corestarter.domain.entity.template.response.ListResponse;
import com.focuz.corestarter.domain.entity.template.response.PageResponse;
import com.focuz.corestarter.domain.entity.template.response.ValueResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/auth-groups")
public interface AuthGroupApi {
    @PostMapping
    ListResponse<AuthGroupResponse> createList(@RequestBody @Validated AuthGroupRequest.AuthGroupListRequest request);

    @GetMapping("/{authGroupCode}")
    ValueResponse<AuthGroupResponse> getDetailByAuthGroupCode(@PathVariable String authGroupCode);

    @GetMapping
    PageResponse<AuthGroupResponse> getPage(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "authGroupCodes", defaultValue = "")
            List<String> authGroupCodes,
            @RequestParam(required = false, value = "statusCodes", defaultValue = "")
            List<String> statusCodes,
            @RequestParam(required = false, value = "sorter", defaultValue = "createdAt_desc")
            String sorter,
            @RequestParam(required = false, value = "current", defaultValue = "0")
            Integer current,
            @RequestParam(required = false, value = "pageSize", defaultValue = "25")
            Integer pageSize
    );

    @PutMapping("/{authGroupCode}")
    ValueResponse<AuthGroupResponse> updateByCode(@PathVariable String authGroupCode, @RequestBody @Validated AuthGroupRequest request);

    @PutMapping("/{authGroupCode}/permissions")
    ValueResponse<?> addPermissionList(@PathVariable String authGroupCode, @RequestBody @Validated PermissionRequest.PermissionCodeListRequest request);

    @PatchMapping("/{authGroupCode}/active")
    ValueResponse<AuthGroupResponse> activeByCode(@PathVariable String authGroupCode);

    @PatchMapping("/{authGroupCode}/inactive")
    ValueResponse<AuthGroupResponse> inactiveByCode(@PathVariable String authGroupCode);

    @DeleteMapping
    ListResponse<?> removeListByCodeIn(@RequestBody @Validated AuthGroupRequest.AuthGroupCodeListRequest request);
}
