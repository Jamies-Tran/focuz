package com.focuz.authservice.adapter.controller.client;

import com.focuz.authservice.adapter.api.client.ClientApi;
import com.focuz.authservice.application.dto.request.client.ClientRequest;
import com.focuz.authservice.application.dto.response.client.ClientResponse;
import com.focuz.authservice.application.mapper.request.client.ClientRequestMapper;
import com.focuz.authservice.application.mapper.response.client.ClientResponseMapper;
import com.focuz.authservice.domain.constant.enums.error.EAppError;
import com.focuz.authservice.domain.entity.client.ClientCriteria;
import com.focuz.authservice.domain.service.client.ClientService;
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
                                requestMapper.toDomain(
                                        request.clients()
                                )
                        )
                )
        );
    }

    @Override
    public ValueResponse<ClientResponse> getDetailByCode(String clientCode) {
        return ValueResponse.success(
                responseMapper.toDto(
                        clientService.getDetailByCode(clientCode)
                                .orElseThrow(() ->new ApplicationException(EAppError.CLIENT_NOT_FOUND, HttpStatus.NOT_FOUND) )
                )
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
    public ValueResponse<ClientResponse> activeByCode(String clientCode) {
        return ValueResponse.success(
                responseMapper.toDto(
                        clientService.activeByCode(clientCode)
                )
        );
    }

    @Override
    public ValueResponse<ClientResponse> inactiveByCode(String clientCode) {
        return ValueResponse.success(
                responseMapper.toDto(
                        clientService.inactiveByCode(clientCode)
                )
        );
    }

    @Override
    public ValueResponse<?> deleteListByCodeIn(ClientRequest.ClientCodeListRequest request) {
        clientService.deleteListByCodeIn(request.clientCodes());
        return ValueResponse.success(request.clientCodes());
    }
}
