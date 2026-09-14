package com.focuz.authservice.domain.repository.clientscope;

import com.focuz.authservice.domain.entity.clientscope.ClientScope;
import com.focuz.authservice.domain.entity.clientscope.ClientScopeCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ClientScopeRepository {
    List<ClientScope> saveAll(List<ClientScope> clientScopes);
    Page<ClientScope> findAll(ClientScopeCriteria criteria, PageRequest pageRequest);
    void deleteAllByClientId(Long clientId);
}
