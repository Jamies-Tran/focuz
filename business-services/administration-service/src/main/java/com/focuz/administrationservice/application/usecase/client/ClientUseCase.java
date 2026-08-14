package com.focuz.administrationservice.application.usecase.client;

import com.focuz.administrationservice.domain.constant.enums.client.EClientStatus;
import com.focuz.administrationservice.domain.constant.enums.error.EAppError;
import com.focuz.administrationservice.domain.entity.client.Client;
import com.focuz.administrationservice.domain.entity.client.ClientCriteria;
import com.focuz.administrationservice.domain.repository.client.ClientRepository;
import com.focuz.administrationservice.domain.service.client.ClientService;
import com.focuz.corestarter.domain.entity.exception.ApplicationException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientUseCase implements ClientService {
    ClientRepository repository;

    @Override
    @Transactional
    public List<Client> createList(List<Client> clients) {
        validateCreateList(clients);
        return repository.saveAll(clients);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> getDetailByCode(String clientCode) {
        return repository.findByClientCode(clientCode);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Client> getPage(ClientCriteria criteria) {
        return repository.findAll(criteria, criteria.pageRequest());
    }

    @Override
    @Transactional
    public Client updateByCode(String clientCode, Client client) {
        validateUpdateByCode(clientCode, client);
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
    public void removeListByCodeIn(List<String> clientCodes) {
        repository.deleteAllByClientCodeIn(clientCodes);
    }

    private void validateCreateList(List<Client> clients) {
        List<String> clientCodes = clients.stream()
                .map(Client::clientCode)
                .filter(StringUtils::hasText)
                .toList();
        if(new HashSet<>(clientCodes).size() != clients.stream().filter(c -> StringUtils.hasText(c.clientCode())).count()) {
            throw new ApplicationException(EAppError.CLIENT_DUPLICATE_IN_LIST, HttpStatus.BAD_REQUEST);
        }
        if(repository.existsAllByClientCodeIn(clientCodes)) {
            throw new ApplicationException(EAppError.CLIENT_DUPLICATE_IN_DB, HttpStatus.BAD_REQUEST);
        }
    }

    private void validateUpdateByCode(String clientCode, Client client) {
        repository.findByClientCode(clientCode)
                .ifPresent(c -> {
                    if(!Objects.equals(client.clientCode(),  c.clientCode())) {
                        if(repository.existsAllByClientCodeIn(List.of(client.clientCode()))) {
                            throw new ApplicationException(EAppError.CLIENT_DUPLICATE_IN_DB, HttpStatus.BAD_REQUEST);
                        }
                    }
                });
    }
}
