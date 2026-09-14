package com.focuz.authservice.application.usecase.clientscope;

import com.focuz.authservice.domain.entity.clientscope.ClientScope;
import com.focuz.authservice.domain.entity.clientscope.ClientScopeCriteria;
import com.focuz.authservice.domain.repository.clientscope.ClientScopeRepository;
import com.focuz.authservice.domain.service.clientscope.ClientScopeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientScopeUseCase implements ClientScopeService {
    ClientScopeRepository repository;

    @Override
    @Transactional
    public List<ClientScope> createList(Long clientId, List<Long> scopeIds) {
        return repository.saveAll(
                ClientScope.of(clientId, scopeIds)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClientScope> getPage(ClientScopeCriteria criteria) {
        return repository.findAll(criteria, criteria.pageRequest());
    }

    @Override
    @Transactional
    public void removeListByClientId(Long clientId) {
        repository.deleteAllByClientId(clientId);
    }
}
