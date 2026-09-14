package com.focuz.authservice.adapter.api.application;

import com.focuz.authservice.application.dto.request.application.AuthApplicationRequest;
import com.focuz.authservice.application.dto.response.application.AuthApplicationResponse;
import com.focuz.corestarter.domain.entity.template.response.ListResponse;
import com.focuz.corestarter.domain.entity.template.response.PageResponse;
import com.focuz.corestarter.domain.entity.template.response.ValueResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/auth-application")
public interface AuthApplicationApi {
    @PostMapping
    ListResponse<AuthApplicationResponse> createList(@RequestBody @Validated AuthApplicationRequest.AuthApplicationListRequest request);

    @GetMapping
    PageResponse<AuthApplicationResponse> getPage(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "applicationCodes", defaultValue = "")
            List<String> applicationCodes,
            @RequestParam(required = false, value = "statusCodes", defaultValue = "")
            List<String> statusCodes,
            @RequestParam(required = false, value = "sorter", defaultValue = "updatedAt_desc")
            String sorter,
            @RequestParam(required = false, value = "current", defaultValue = "0")
            Integer current,
            @RequestParam(required = false, value = "pageSize", defaultValue = "25")
            Integer pageSize
    );

    @GetMapping("/{applicationCode}")
    ValueResponse<AuthApplicationResponse> getDetailByCode(@PathVariable String applicationCode);

    @PutMapping("/{applicationCode}")
    ValueResponse<AuthApplicationResponse> updateByCode(@PathVariable String applicationCode, @RequestBody @Validated AuthApplicationRequest request);

    @PatchMapping("/{applicationCode}/active")
    ValueResponse<AuthApplicationResponse> activeByCode(@PathVariable String applicationCode);

    @PatchMapping("/{applicationCode}/inactive")
    ValueResponse<AuthApplicationResponse> inactiveByCode(@PathVariable String applicationCode);

    @DeleteMapping
    ValueResponse<?> removeListByCodeIn(@RequestBody @Validated AuthApplicationRequest.AuthApplicationCodeListRequest request);
}
