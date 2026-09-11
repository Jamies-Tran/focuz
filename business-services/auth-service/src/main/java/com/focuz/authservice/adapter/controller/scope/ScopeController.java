package com.focuz.authservice.adapter.controller.scope;

import com.focuz.authservice.adapter.api.scope.ScopeApi;
import com.focuz.authservice.application.dto.request.scope.ScopeRequest;
import com.focuz.authservice.application.dto.response.scope.ScopeResponse;
import com.focuz.authservice.application.mapper.request.scope.ScopeRequestMapper;
import com.focuz.authservice.application.mapper.response.scope.ScopeResponseMapper;
import com.focuz.authservice.domain.constant.enums.error.EAppError;
import com.focuz.authservice.domain.entity.scope.ScopeCriteria;
import com.focuz.authservice.domain.service.scope.ScopeService;
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
public class ScopeController implements ScopeApi {
    ScopeService scopeService;
    ScopeRequestMapper requestMapper;
    ScopeResponseMapper responseMapper;

    @Override
    public ListResponse<ScopeResponse> createList(ScopeRequest.ScopeListRequest request) {
        return ListResponse.success(
                responseMapper.toDto(
                        scopeService.createList(requestMapper.toDomain(request.scopes()))
                )
        );
    }

    @Override
    public PageResponse<ScopeResponse> getPage(
            String search,
            List<String> scopeCodes,
            List<String> statusCodes,
            String sorter,
            Integer current,
            Integer pageSize
    ) {
        ScopeCriteria criteria = ScopeCriteria.builder()
                .search(search)
                .scopeCodes(scopeCodes)
                .statusCodes(statusCodes)
                .sorter(sorter)
                .current(current)
                .pageSize(pageSize)
                .build();
        return PageResponse.success(
                scopeService.getPage(criteria)
                        .map(responseMapper::toDto)
        );
    }

    @Override
    public ValueResponse<ScopeResponse> getDetail(String scopeCode) {
        return ValueResponse.success(
                scopeService.getDetail(scopeCode)
                        .map(responseMapper::toDto)
                        .orElseThrow(() -> new ApplicationException(EAppError.SCOPE_NOT_FOUND, HttpStatus.NOT_FOUND))
        );
    }

    @Override
    public ValueResponse<ScopeResponse> updateByCode(String scopeCode, ScopeRequest request) {
        return ValueResponse.success(
                responseMapper.toDto(
                        scopeService.updateByCode(scopeCode, requestMapper.toDomain(request))
                )
        );
    }

    @Override
    public ValueResponse<ScopeResponse> activeByCode(String scopeCode) {
        return ValueResponse.success(
                responseMapper.toDto(
                        scopeService.activeByCode(scopeCode)
                )
        );
    }

    @Override
    public ValueResponse<ScopeResponse> inactiveByCode(String scopeCode) {
        return ValueResponse.success(
                responseMapper.toDto(
                        scopeService.inactiveByCode(scopeCode)
                )
        );
    }

    @Override
    public ValueResponse<?> removeListByScopeCodeIn(ScopeRequest.ScopeCodeListRequest request) {
        scopeService.removeListByCodeIn(request.scopeCodes());
        return ValueResponse.success(request.scopeCodes());
    }
}
