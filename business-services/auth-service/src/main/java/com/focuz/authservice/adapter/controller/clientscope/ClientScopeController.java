package com.focuz.authservice.adapter.controller.clientscope;

import com.focuz.authservice.adapter.api.clientscope.ClientScopeApi;
import com.focuz.authservice.application.dto.response.clientscope.ClientScopeResponse;
import com.focuz.authservice.application.mapper.response.clientscope.ClientScopeResponseMapper;
import com.focuz.authservice.domain.entity.clientscope.ClientScopeCriteria;
import com.focuz.authservice.domain.service.clientscope.ClientScopeService;
import com.focuz.corestarter.domain.entity.template.response.PageResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientScopeController implements ClientScopeApi {
    ClientScopeService clientScopeService;
    ClientScopeResponseMapper responseMapper;

    @Override
    public PageResponse<ClientScopeResponse> getPage(
            Long clientId,
            String search,
            List<String> scopeCodes,
            String sorter,
            Integer current,
            Integer pageSize
    ) {
        ClientScopeCriteria criteria = ClientScopeCriteria.builder()
                .clientId(clientId)
                .search(search)
                .scopeCodes(scopeCodes)
                .sorter(sorter)
                .current(current)
                .pageSize(pageSize)
                .build();
        return PageResponse.success(
                clientScopeService.getPage(criteria)
                        .map(responseMapper::toDto)
        );
    }
}
