package com.focuz.administrationservice.adapter.api.client;

import com.focuz.administrationservice.application.dto.request.client.ClientRequest;
import com.focuz.administrationservice.application.dto.response.client.ClientResponse;
import com.focuz.corestarter.domain.entity.template.response.ListResponse;
import com.focuz.corestarter.domain.entity.template.response.PageResponse;
import com.focuz.corestarter.domain.entity.template.response.ValueResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/clients")
public interface ClientApi {
    @PostMapping
    ListResponse<ClientResponse> createList(@RequestBody @Validated ClientRequest.ClientListRequest request);

    @GetMapping("/{clientCode}")
    ValueResponse<ClientResponse> getDetailByCode(@PathVariable String clientCode);

    @GetMapping
    PageResponse<ClientResponse> getPage(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "clientCodes", defaultValue = "")
            List<String> clientCodes,
            @RequestParam(required = false, value = "statusCodes", defaultValue = "")
            List<String> statusCodes,
            @RequestParam(required = false, value = "sorter", defaultValue = "createdAt_desc")
            String sorter,
            @RequestParam(required = false, value = "current", defaultValue = "0")
            Integer current,
            @RequestParam(required = false, value = "pageSize", defaultValue = "25")
            Integer pageSize
    );

    @PutMapping("/{clientCode}")
    ValueResponse<ClientResponse> updateByCode(@PathVariable String clientCode, @RequestBody @Validated ClientRequest request);

    @PatchMapping("/{clientCode}/active")
    ValueResponse<ClientResponse> active(@PathVariable String clientCode);

    @PatchMapping("/{clientCode}/inactive")
    ValueResponse<ClientResponse> inactive(@PathVariable String clientCode);

    @DeleteMapping
    ValueResponse<?> removeListByCodeIn(@RequestBody @Validated ClientRequest.ClientCodeListRequest request);
}
