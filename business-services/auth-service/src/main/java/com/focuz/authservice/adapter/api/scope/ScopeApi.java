package com.focuz.authservice.adapter.api.scope;

import com.focuz.authservice.application.dto.request.scope.ScopeRequest;
import com.focuz.authservice.application.dto.response.scope.ScopeResponse;
import com.focuz.corestarter.domain.entity.template.response.ListResponse;
import com.focuz.corestarter.domain.entity.template.response.PageResponse;
import com.focuz.corestarter.domain.entity.template.response.ValueResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/scopes")
public interface ScopeApi {
    @PostMapping
    ListResponse<ScopeResponse> createList(@RequestBody @Validated ScopeRequest.ScopeListRequest request);

    @GetMapping
    PageResponse<ScopeResponse> getPage(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "scopeCodes", defaultValue = "")
            List<String> scopeCodes,
            @RequestParam(required = false, value = "statusCodes", defaultValue = "")
            List<String> statusCodes,
            @RequestParam(required = false, value = "sorter", defaultValue = "updatedAt_desc")
            String sorter,
            @RequestParam(required = false, value = "current", defaultValue = "0")
            Integer current,
            @RequestParam(required = false, value = "pageSize", defaultValue = "25")
            Integer pageSize
    );

    @GetMapping("/{scopeCode}")
    ValueResponse<ScopeResponse> getDetail(@PathVariable String scopeCode);

    @PutMapping("/{scopeCode}")
    ValueResponse<ScopeResponse> updateByCode(@PathVariable String scopeCode, @RequestBody @Validated ScopeRequest request);

    @PatchMapping("/{scopeCode}/active")
    ValueResponse<ScopeResponse> activeByCode(@PathVariable String scopeCode);

    @PatchMapping("/{scopeCode}/inactive")
    ValueResponse<ScopeResponse> inactiveByCode(@PathVariable String scopeCode);

    @DeleteMapping
    ValueResponse<?> removeListByScopeCodeIn(@RequestBody @Validated ScopeRequest.ScopeCodeListRequest request);
}
