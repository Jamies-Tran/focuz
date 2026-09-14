package com.focuz.authservice.adapter.controller.application;

import com.focuz.authservice.adapter.api.application.AuthApplicationApi;
import com.focuz.authservice.application.dto.request.application.AuthApplicationRequest;
import com.focuz.authservice.application.dto.response.application.AuthApplicationResponse;
import com.focuz.authservice.application.mapper.request.application.AuthApplicationRequestMapper;
import com.focuz.authservice.application.mapper.response.application.AuthApplicationResponseMapper;
import com.focuz.authservice.domain.constant.enums.error.EAppError;
import com.focuz.authservice.domain.entity.application.AuthApplicationCriteria;
import com.focuz.authservice.domain.service.application.AuthApplicationService;
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
public class AuthApplicationController implements AuthApplicationApi {
    AuthApplicationService authApplicationService;
    AuthApplicationRequestMapper requestMapper;
    AuthApplicationResponseMapper responseMapper;

    @Override
    public ListResponse<AuthApplicationResponse> createList(AuthApplicationRequest.AuthApplicationListRequest request) {
        return ListResponse.success(
                responseMapper.toDto(
                        authApplicationService.createList(
                                requestMapper.toDomain(request.applications())
                        )
                )
        );
    }

    @Override
    public PageResponse<AuthApplicationResponse> getPage(
            String search,
            List<String> applicationCodes,
            List<String> statusCodes,
            String sorter,
            Integer current,
            Integer pageSize
    ) {
        AuthApplicationCriteria criteria = AuthApplicationCriteria.builder()
                .search(search)
                .authApplicationCodes(applicationCodes)
                .statusCodes(statusCodes)
                .sorter(sorter)
                .current(current)
                .pageSize(pageSize)
                .build();
        return PageResponse.success(
                authApplicationService.getPage(criteria)
                        .map(responseMapper::toDto)
        );
    }

    @Override
    public ValueResponse<AuthApplicationResponse> getDetailByCode(String applicationCode) {
        return ValueResponse.success(
                authApplicationService.getDetailByCode(applicationCode)
                        .map(responseMapper::toDto)
                        .orElseThrow(() -> new ApplicationException(EAppError.APPLICATION_NOT_FOUND, HttpStatus.NOT_FOUND))
        );
    }

    @Override
    public ValueResponse<AuthApplicationResponse> updateByCode(String applicationCode, AuthApplicationRequest request) {
        return ValueResponse.success(
                responseMapper.toDto(
                        authApplicationService.updateByCode(applicationCode, requestMapper.toDomain(request))
                )
        );
    }

    @Override
    public ValueResponse<AuthApplicationResponse> activeByCode(String applicationCode) {
        return ValueResponse.success(
               responseMapper.toDto(
                       authApplicationService.activeByCode(applicationCode)
               )
        );
    }

    @Override
    public ValueResponse<AuthApplicationResponse> inactiveByCode(String applicationCode) {
        return ValueResponse.success(
                responseMapper.toDto(
                        authApplicationService.inactiveByCode(applicationCode)
                )
        );
    }

    @Override
    public ValueResponse<?> removeListByCodeIn(AuthApplicationRequest.AuthApplicationCodeListRequest request) {
        authApplicationService.removeListByCodeIn(request.applicationCodes());
        return ValueResponse.success(request.applicationCodes());
    }
}
