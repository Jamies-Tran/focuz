package com.focuz.authservice.domain.service.client;

import com.focuz.authservice.domain.entity.client.Client;
import com.focuz.authservice.domain.entity.client.ClientCriteria;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> createList(List<Client> clients);
    Optional<Client> getDetailByCode(String clientCode);
    Optional<Client> getDetailBySecret(String clientSecret);
    Page<Client> getPage(ClientCriteria criteria);
    Client updateByCode(String clientCode, Client client);
    Client activeByCode(String clientCode);
    Client inactiveByCode(String clientCode);
    void deleteListByCodeIn(List<String> clientCodes);
    void addScopeList(Long clientId, List<String> scopeCodes);
}
