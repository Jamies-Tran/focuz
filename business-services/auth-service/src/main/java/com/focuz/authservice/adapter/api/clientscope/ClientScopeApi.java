package com.focuz.authservice.adapter.api.clientscope;

import com.focuz.authservice.application.dto.response.clientscope.ClientScopeResponse;
import com.focuz.corestarter.domain.entity.template.response.PageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/v1/client-scope")
public interface ClientScopeApi {
    @GetMapping("/{clientId}")
    PageResponse<ClientScopeResponse> getPage(
            @PathVariable
            Long clientId,
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "scopeCodes", defaultValue = "")
            List<String> scopeCodes,
            @RequestParam(required = false, value = "sorter", defaultValue = "updatedAt_desc")
            String sorter,
            @RequestParam(required = false, value = "current", defaultValue = "0")
            Integer current,
            @RequestParam(required = false, value = "pageSize", defaultValue = "25")
            Integer pageSize
    );
}
