package com.focuz.authservice.application.usecase.client;

import com.focuz.authservice.domain.constant.enums.client.EClientStatus;
import com.focuz.authservice.domain.constant.enums.error.EAppError;
import com.focuz.authservice.domain.entity.client.Client;
import com.focuz.authservice.domain.entity.client.ClientCriteria;
import com.focuz.authservice.domain.repository.client.ClientRepository;
import com.focuz.authservice.domain.service.client.ClientService;
import com.focuz.authservice.domain.service.clientscope.ClientScopeService;
import com.focuz.authservice.domain.service.scope.ScopeService;
import com.focuz.corestarter.domain.entity.exception.ApplicationException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientUseCase implements ClientService {
    ClientRepository repository;
    ClientScopeService clientScopeService;
    ScopeService scopeService;

    @Override
    @Transactional
    public List<Client> createList(List<Client> clients) {
        return repository.saveAll(clients);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> getDetailByCode(String clientCode) {
        return repository.findByClientCode(clientCode);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> getDetailBySecret(String clientSecret) {
        return repository.findByClientSecret(clientSecret);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Client> getPage(ClientCriteria criteria) {
        return repository.findAll(criteria, criteria.pageRequest());
    }

    @Override
    @Transactional
    public Client updateByCode(String clientCode, Client client) {
        return repository.updateByClientCode(clientCode, client)
                .orElseThrow(() -> new ApplicationException(EAppError.CLIENT_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public Client activeByCode(String clientCode) {
        return repository.updateByClientCode(clientCode, EClientStatus.ACTIVE)
                .orElseThrow(() -> new ApplicationException(EAppError.CLIENT_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public Client inactiveByCode(String clientCode) {
        return repository.updateByClientCode(clientCode, EClientStatus.INACTIVE)
                .orElseThrow(() -> new ApplicationException(EAppError.CLIENT_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public void deleteListByCodeIn(List<String> clientCodes) {
        repository.deleteAllByClientCodeIn(clientCodes);
    }

    @Override
    @Transactional
    public void addScopeList(Long clientId, List<String> scopeCodes) {
        clientScopeService.createList(clientId, scopeService.getScopeIdListByScopeCodeIn(scopeCodes));
    }
}
