package com.focuz.administrationservice.adapter.controller.permission;

import com.focuz.administrationservice.adapter.api.permission.PermissionApi;
import com.focuz.administrationservice.application.dto.request.permission.PermissionRequest;
import com.focuz.administrationservice.application.dto.response.permission.PermissionResponse;
import com.focuz.administrationservice.application.mapper.request.permission.PermissionRequestMapper;
import com.focuz.administrationservice.application.mapper.response.permission.PermissionResponseMapper;
import com.focuz.administrationservice.domain.constant.enums.error.EAppError;
import com.focuz.administrationservice.domain.entity.permission.PermissionCriteria;
import com.focuz.administrationservice.domain.service.permission.PermissionService;
import com.focuz.corestarter.domain.entity.exception.ApplicationException;
import com.focuz.corestarter.domain.entity.template.response.ListResponse;
import com.focuz.corestarter.domain.entity.template.response.PageResponse;
import com.focuz.corestarter.domain.entity.template.response.ValueResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController implements PermissionApi {
    PermissionService permissionService;
    PermissionRequestMapper requestMapper;
    PermissionResponseMapper responseMapper;

    @Override
    public ListResponse<PermissionResponse> createList(PermissionRequest.PermissionListRequest request) {
        return ListResponse.success(responseMapper.toDomain(permissionService
                .createList(requestMapper.toDto(request.permissionRequestList()))));
    }

    @Override
    public ValueResponse<PermissionResponse> getDetailByCode(String permissionCode) {
        return ValueResponse.success(permissionService.getDetailByCode(permissionCode)
                .map(responseMapper::toDomain)
                .orElseThrow(() -> new ApplicationException(EAppError.PERMISSION_NOT_FOUND, HttpStatus.NOT_FOUND)));
    }

    @Override
    public PageResponse<PermissionResponse> getPage(
            String search,
            List<String> permissionCodes,
            List<String> statusCodes,
            String sorter,
            Integer current,
            Integer pageSize
    ) {
        PermissionCriteria criteria = PermissionCriteria.builder()
                .search(search)
                .permissionCodes(permissionCodes)
                .statusCodes(statusCodes)
                .sorter(sorter)
                .current(current)
                .pageSize(pageSize)
                .build();
        return PageResponse.success(permissionService.getPage(criteria).map(responseMapper::toDomain));
    }

    @Override
    public ValueResponse<PermissionResponse> updateByCode(String permissionCode, PermissionRequest request) {
        return ValueResponse.success(responseMapper.toDomain(permissionService
                .updateByCode(permissionCode, requestMapper.toDto(request))));
    }

    @Override
    public ValueResponse<PermissionResponse> activeByCode(String permissionCode) {
        return ValueResponse.success(responseMapper
                .toDomain(permissionService.activeByCode(permissionCode)));
    }

    @Override
    public ValueResponse<PermissionResponse> inactiveByCode(String permissionCode) {
        return ValueResponse.success(responseMapper
                .toDomain(permissionService.inactiveByCode(permissionCode)));
    }

    @Override
    public ListResponse<?> removeListByCodeIn(PermissionRequest.PermissionCodeListRequest request) {
        permissionService.removeListByCodeIn(request.permissionCodeList());
        return ListResponse.success(request.permissionCodeList());
    }
}
