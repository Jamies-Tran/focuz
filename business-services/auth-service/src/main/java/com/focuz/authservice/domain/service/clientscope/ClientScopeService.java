package com.focuz.authservice.domain.service.clientscope;

import com.focuz.authservice.domain.entity.clientscope.ClientScope;
import com.focuz.authservice.domain.entity.clientscope.ClientScopeCriteria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClientScopeService {
    List<ClientScope> createList(Long clientId, List<Long> scopeIds);
    Page<ClientScope> getPage(ClientScopeCriteria criteria);
    void removeListByClientId(Long clientId);
}
