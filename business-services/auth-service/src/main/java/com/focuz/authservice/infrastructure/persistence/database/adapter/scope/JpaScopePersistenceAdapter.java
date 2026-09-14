package com.focuz.authservice.infrastructure.persistence.database.adapter.scope;

import com.focuz.authservice.domain.constant.enums.scope.EScopeStatus;
import com.focuz.authservice.domain.entity.scope.Scope;
import com.focuz.authservice.domain.entity.scope.ScopeCriteria;
import com.focuz.authservice.domain.repository.scope.ScopeRepository;
import com.focuz.authservice.infrastructure.persistence.database.mapper.scope.ScopeEntityMapper;
import com.focuz.authservice.infrastructure.persistence.database.repository.scope.JpaScopeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JpaScopePersistenceAdapter implements ScopeRepository {
    JpaScopeRepository repository;
    ScopeEntityMapper mapper;

    @Override
    public List<Scope> saveAll(List<Scope> scopes) {
        return mapper.toDomain(
                repository.saveAll(mapper.toEntity(scopes))
        );
    }

    @Override
    public List<Long> findAllScopeIdByScopeCodeIn(List<String> scopeCodes) {
        return repository.findAllScopeIdByScopeCodeIn(scopeCodes);
    }

    @Override
    public Page<Scope> findAll(ScopeCriteria criteria, PageRequest pageRequest) {
        return repository.findAll(criteria, pageRequest)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Scope> findByScopeCode(String scopeCode) {
        return repository.findByScopeCode(scopeCode)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Scope> updateByScopeCode(String scopeCode, Scope scope) {
        return repository.findByScopeCode(scopeCode)
                .map(s -> {
                    mapper.update(s, scope);
                    return mapper.toDomain(
                            repository.save(s)
                    );
                });
    }

    @Override
    public Optional<Scope> updateByScopeCode(String scopeCode, EScopeStatus status) {
        return repository.findByScopeCode(scopeCode)
                .map(s -> {
                    s.setStatusCode(status.getCode());
                    s.setStatusName(status.getName());
                    return mapper.toDomain(
                            repository.save(s)
                    );
                });
    }

    @Override
    public void deleteAllByScopeCodeIn(List<String> scopeCodes) {
        repository.deleteAll(
                repository.findAllByScopeCodeIn(scopeCodes)
        );
    }

    @Override
    public Boolean existsByScopeCodeIn(List<String> scopeCodes) {
        return repository.existsAllByScopeCodeIn(scopeCodes);
    }
}
