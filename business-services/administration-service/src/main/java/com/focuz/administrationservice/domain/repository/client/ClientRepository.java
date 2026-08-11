package com.focuz.administrationservice.domain.repository.client;

import com.focuz.administrationservice.domain.constant.enums.client.EClientStatus;
import com.focuz.administrationservice.domain.entity.client.Client;
import com.focuz.administrationservice.domain.entity.client.ClientCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    List<Client> saveAll(List<Client> clients);
    Optional<Client> findByClientCode(String clientCode);
    Page<Client> findAll(ClientCriteria criteria, PageRequest pageRequest);
    Optional<Client> updateByClientCode(String clientCode, Client client);
    Optional<Client> updateByClientCode(String clientCode, EClientStatus status);
    void deleteAllByClientCodeIn(List<String> clientCodes);
}
