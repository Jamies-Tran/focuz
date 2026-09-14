package com.focuz.authservice.infrastructure.persistence.database.adapter.clientscope;

import com.focuz.authservice.domain.entity.clientscope.ClientScope;
import com.focuz.authservice.domain.entity.clientscope.ClientScopeCriteria;
import com.focuz.authservice.domain.repository.clientscope.ClientScopeRepository;
import com.focuz.authservice.infrastructure.persistence.database.entity.clientscope.ClientScopeEntity;
import com.focuz.authservice.infrastructure.persistence.database.mapper.clientscope.ClientScopeDaoMapper;
import com.focuz.authservice.infrastructure.persistence.database.mapper.clientscope.ClientScopeEntityMapper;
import com.focuz.authservice.infrastructure.persistence.database.repository.clientscope.JpaClientScopeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JpaClientScopePersistenceAdapter implements ClientScopeRepository {
    JpaClientScopeRepository repository;
    ClientScopeEntityMapper mapper;
    ClientScopeDaoMapper daoMapper;

    @Override
    public List<ClientScope> saveAll(List<ClientScope> clientScopes) {
        return mapper.toDomain(
                repository.saveAll(
                        mapper.toEntity(clientScopes)
                )
        );
    }

    @Override
    public Page<ClientScope> findAll(ClientScopeCriteria criteria, PageRequest pageRequest) {
        return repository.findAll(criteria, pageRequest)
                .map(daoMapper::toDomain);
    }

    @Override
    public void deleteAllByClientId(Long clientId) {
        repository.deleteAll(
                repository.findAllByClientId(clientId)
        );
    }
}
