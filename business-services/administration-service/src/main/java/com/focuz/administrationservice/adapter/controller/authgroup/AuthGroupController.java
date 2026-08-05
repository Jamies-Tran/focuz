package com.focuz.administrationservice.adapter.controller.authgroup;

import com.focuz.administrationservice.adapter.api.authgroup.AuthGroupApi;
import com.focuz.administrationservice.application.dto.request.authgroup.AuthGroupRequest;
import com.focuz.administrationservice.application.dto.response.authgroup.AuthGroupResponse;
import com.focuz.administrationservice.application.mapper.request.authgroup.AuthGroupRequestMapper;
import com.focuz.administrationservice.application.mapper.response.authgroup.AuthGroupResponseMapper;
import com.focuz.administrationservice.domain.constant.enums.error.EAppError;
import com.focuz.administrationservice.domain.entity.authgroup.AuthGroupCriteria;
import com.focuz.administrationservice.domain.service.authgroup.AuthGroupService;
import com.focuz.corestarter.domain.entity.exception.ApplicationException;
import com.focuz.corestarter.domain.entity.template.response.ListResponse;
import com.focuz.corestarter.domain.entity.template.response.PageResponse;
import com.focuz.corestarter.domain.entity.template.response.ValueResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthGroupController implements AuthGroupApi {
    AuthGroupService authGroupService;
    AuthGroupRequestMapper requestMapper;
    AuthGroupResponseMapper responseMapper;

    @Override
    public ListResponse<AuthGroupResponse> createList(AuthGroupRequest.AuthGroupListRequest request) {
        return ListResponse.success(responseMapper
                .toDomain(authGroupService.createList(requestMapper
                        .toDto(request.requestList()))));
    }

    @Override
    public ValueResponse<AuthGroupResponse> getDetailByAuthGroupCode(String authGroupCode) {
        return ValueResponse.success(authGroupService.getDetailByCode(authGroupCode)
                .map(responseMapper::toDomain)
                .orElseThrow(() -> new ApplicationException(EAppError.AUTH_GROUP_NOT_FOUND, HttpStatus.NOT_FOUND)));
    }

    @Override
    public PageResponse<AuthGroupResponse> getPage(
            String search,
            List<String> authGroupCodes,
            List<String> statusCodes,
            String sorter,
            Integer current,
            Integer pageSize
    ) {
        AuthGroupCriteria criteria = AuthGroupCriteria.builder()
                .search(search)
                .authGroupCodes(authGroupCodes)
                .statusCodes(statusCodes)
                .sorter(sorter)
                .current(current)
                .pageSize(pageSize)
                .build();
        return PageResponse.success(authGroupService.getPage(criteria)
                .map(responseMapper::toDomain));
    }

    @Override
    public ValueResponse<AuthGroupResponse> updateByCode(String authGroupCode, AuthGroupRequest request) {
        return ValueResponse.success(responseMapper
                .toDomain(authGroupService.updateByCode(authGroupCode,
                        requestMapper.toDto(request))));
    }

    @Override
    public ValueResponse<AuthGroupResponse> activeByCode(String authGroupCode) {
        return ValueResponse.success(responseMapper.toDomain(authGroupService.activeByCode(authGroupCode)));
    }

    @Override
    public ValueResponse<AuthGroupResponse> inactiveByCode(String authGroupCode) {
        return ValueResponse.success(responseMapper.toDomain(authGroupService.inactiveByCode(authGroupCode)));
    }

    @Override
    public ListResponse<?> removeListByCodeIn(AuthGroupRequest.AuthGroupCodeListRequest request) {
        authGroupService.removeListByCodeIn(request.authGroupCodeList());
        return ListResponse.success(request.authGroupCodeList());
    }
}
