package com.focuz.authservice.application.usecase.scope;

import com.focuz.authservice.domain.constant.enums.error.EAppError;
import com.focuz.authservice.domain.constant.enums.scope.EScopeStatus;
import com.focuz.authservice.domain.entity.scope.Scope;
import com.focuz.authservice.domain.entity.scope.ScopeCriteria;
import com.focuz.authservice.domain.repository.scope.ScopeRepository;
import com.focuz.authservice.domain.service.scope.ScopeService;
import com.focuz.corestarter.domain.entity.exception.ApplicationException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScopeUseCase implements ScopeService {
    ScopeRepository repository;

    @Override
    @Transactional
    public List<Scope> createList(List<Scope> scopes) {
        validateCreateList(scopes);
        return repository.saveAll(scopes);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Scope> getPage(ScopeCriteria criteria) {
        return repository.findAll(criteria, criteria.pageRequest());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Scope> getDetail(String scopeCode) {
        return repository.findByScopeCode(scopeCode);
    }

    @Override
    @Transactional
    public Scope updateByCode(String scopeCode, Scope scope) {
        validateUpdateByScopeCode(scopeCode, scope);
        return repository.updateByScopeCode(scopeCode, scope)
                .orElseThrow(() -> new ApplicationException(EAppError.SCOPE_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public Scope activeByCode(String scopeCode) {
        return repository.updateByScopeCode(scopeCode, EScopeStatus.ACTIVE)
                .orElseThrow(() -> new ApplicationException(EAppError.SCOPE_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public Scope inactiveByCode(String scopeCode) {
        return repository.updateByScopeCode(scopeCode, EScopeStatus.INACTIVE)
                .orElseThrow(() -> new ApplicationException(EAppError.SCOPE_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public void removeListByCodeIn(List<String> scopeCodes) {
        repository.deleteAllByScopeCodeIn(scopeCodes);
    }

    private void validateCreateList(List<Scope> scopes) {
        Set<String> scopeCodes = new HashSet<>(
                scopes
                        .stream()
                        .map(Scope::scopeCode)
                        .toList()
        );
        if(scopes.size() != scopeCodes.size()) {
            throw new ApplicationException(EAppError.SCOPE_DUPLICATED_IN_LIST,  HttpStatus.BAD_REQUEST);
        }
        if(repository.existsByScopeCodeIn(scopeCodes.stream().toList())) {
            throw new ApplicationException(EAppError.SCOPE_DUPLICATED_IN_DB,  HttpStatus.BAD_REQUEST);
        }
    }

    private void validateUpdateByScopeCode(String scopeCode, Scope scope) {
        repository.findByScopeCode(scopeCode)
                .ifPresent(s -> {
                    if(!Objects.equals(s.scopeCode(), scope.scopeCode())) {
                        if(repository.existsByScopeCodeIn(List.of(scope.scopeCode()))) {
                            throw new ApplicationException(EAppError.SCOPE_DUPLICATED_IN_DB,  HttpStatus.BAD_REQUEST);
                        }
                    }
                });
    }
}
