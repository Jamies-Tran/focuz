package com.focuz.authservice.domain.repository.scope;

import com.focuz.authservice.domain.constant.enums.scope.EScopeStatus;
import com.focuz.authservice.domain.entity.scope.Scope;
import com.focuz.authservice.domain.entity.scope.ScopeCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface ScopeRepository {
    List<Scope> saveAll(List<Scope> scopes);
    Page<Scope> findAll(ScopeCriteria criteria, PageRequest pageRequest);
    Optional<Scope> findByScopeCode(String scopeCode);
    Optional<Scope> updateByScopeCode(String scopeCode, Scope scope);
    Optional<Scope> updateByScopeCode(String scopeCode, EScopeStatus status);
    void deleteAllByScopeCodeIn(List<String> scopeCodes);
    Boolean existsByScopeCodeIn(List<String> scopeCodes);
}
