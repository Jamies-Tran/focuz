package com.focuz.administrationservice.adapter.controller.client;

import com.focuz.administrationservice.adapter.api.client.ClientApi;
import com.focuz.administrationservice.application.dto.request.client.ClientRequest;
import com.focuz.administrationservice.application.dto.response.client.ClientResponse;
import com.focuz.administrationservice.application.mapper.request.client.ClientRequestMapper;
import com.focuz.administrationservice.application.mapper.response.client.ClientResponseMapper;
import com.focuz.administrationservice.domain.constant.enums.error.EAppError;
import com.focuz.administrationservice.domain.entity.client.ClientCriteria;
import com.focuz.administrationservice.domain.service.client.ClientService;
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
public class ClientController implements ClientApi {
    ClientService clientService;
    ClientRequestMapper requestMapper;
    ClientResponseMapper responseMapper;

    @Override
    public ListResponse<ClientResponse> createList(ClientRequest.ClientListRequest request) {
        return ListResponse.success(
                responseMapper.toDto(
                        clientService.createList(
                                requestMapper.toDomain(request.clientList())
                        )
                )
        );
    }

    @Override
    public ValueResponse<ClientResponse> getDetailByCode(String clientCode) {
        return ValueResponse.success(
                clientService.getDetailByCode(clientCode)
                        .map(responseMapper::toDto)
                        .orElseThrow(() -> new ApplicationException(EAppError.CLIENT_NOT_FOUND, HttpStatus.NOT_FOUND))
        );
    }

    @Override
    public PageResponse<ClientResponse> getPage(
            String search,
            List<String> clientCodes,
            List<String> statusCodes,
            String sorter,
            Integer current,
            Integer pageSize
    ) {
        ClientCriteria criteria = ClientCriteria.builder()
                .search(search)
                .clientCodes(clientCodes)
                .statusCodes(statusCodes)
                .sorter(sorter)
                .current(current)
                .pageSize(pageSize)
                .build();
        return PageResponse.success(
                clientService.getPage(criteria)
                        .map(responseMapper::toDto)
        );
    }

    @Override
    public ValueResponse<ClientResponse> updateByCode(String clientCode, ClientRequest request) {
        return ValueResponse.success(
                responseMapper.toDto(
                        clientService.updateByCode(clientCode, requestMapper.toDomain(request))
                )
        );
    }

    @Override
    public ValueResponse<ClientResponse> active(String clientCode) {
        return ValueResponse.success(
                responseMapper.toDto(
                        clientService.activeByCode(clientCode)
                )
        );
    }

    @Override
    public ValueResponse<ClientResponse> inactive(String clientCode) {
        return ValueResponse.success(
                responseMapper.toDto(
                        clientService.inactiveByCode(clientCode)
                )
        );
    }

    @Override
    public ValueResponse<?> removeListByCodeIn(ClientRequest.ClientCodeListRequest request) {
        clientService.removeListByCodeIn(request.clientCodeList());
        return ValueResponse.success(request.clientCodeList());
    }
}
