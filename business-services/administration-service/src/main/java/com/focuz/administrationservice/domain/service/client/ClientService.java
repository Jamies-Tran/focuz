package com.focuz.administrationservice.domain.service.client;

import com.focuz.administrationservice.domain.entity.client.Client;
import com.focuz.administrationservice.domain.entity.client.ClientCriteria;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> createList(List<Client> clients);
    Optional<Client> getDetailByCode(String clientCode);
    Page<Client> getPage(ClientCriteria criteria);
    Client updateByCode(String clientCode, Client client);
    Client activeByCode(String clientCode);
    Client inactiveByCode(String clientCode);
    void removeListByCodeIn(List<String> clientCodes);
}
