package com.focuz.administrationservice.adapter.api.authgroup;

import com.focuz.administrationservice.application.dto.request.authgroup.AuthGroupRequest;
import com.focuz.administrationservice.application.dto.request.permission.PermissionRequest;
import com.focuz.administrationservice.application.dto.request.user.UserRequest;
import com.focuz.administrationservice.application.dto.response.authgroup.AuthGroupResponse;
import com.focuz.administrationservice.application.dto.response.grouppermission.GroupPermissionResponse;
import com.focuz.administrationservice.application.dto.response.usergroup.UserGroupResponse;
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

    @GetMapping("/{authGroupId}/permissions")
    PageResponse<GroupPermissionResponse> getPagePermission(
            @PathVariable
            Long authGroupId,
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "permissionCodes", defaultValue = "")
            List<String> permissionCodes,
            @RequestParam(required = false, value = "sorter", defaultValue = "createdAt_desc")
            String sorter,
            @RequestParam(required = false, value = "current", defaultValue = "0")
            Integer current,
            @RequestParam(required = false, value = "pageSize", defaultValue = "25")
            Integer pageSize
    );

    @GetMapping("/{authGroupId}/users")
    PageResponse<UserGroupResponse> getPageUser(
            @PathVariable
            Long authGroupId,
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
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

    @PutMapping("/{authGroupCode}/users")
    ValueResponse<?> addUser(@PathVariable String authGroupCode, @RequestBody @Validated UserRequest.UserIdListRequest request);

    @PatchMapping("/{authGroupCode}/active")
    ValueResponse<AuthGroupResponse> activeByCode(@PathVariable String authGroupCode);

    @PatchMapping("/{authGroupCode}/inactive")
    ValueResponse<AuthGroupResponse> inactiveByCode(@PathVariable String authGroupCode);

    @DeleteMapping
    ListResponse<?> removeListByCodeIn(@RequestBody @Validated AuthGroupRequest.AuthGroupCodeListRequest request);

    @DeleteMapping("/{authGroupCode}/permissions")
    ValueResponse<?> removePermissionList(@PathVariable String authGroupCode, @RequestBody @Validated PermissionRequest.PermissionCodeListRequest request);

    @DeleteMapping("/{authGroupCode}/users")
    ValueResponse<?> removeUserList(@PathVariable String authGroupCode, @RequestBody @Validated UserRequest.UserIdListRequest request);
}
