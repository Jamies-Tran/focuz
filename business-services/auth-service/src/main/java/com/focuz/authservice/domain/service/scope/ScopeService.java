package com.focuz.authservice.domain.service.scope;

import com.focuz.authservice.domain.entity.scope.Scope;
import com.focuz.authservice.domain.entity.scope.ScopeCriteria;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ScopeService {
    List<Scope> createList(List<Scope> scopes);
    List<Long> getScopeIdListByScopeCodeIn(List<String> scopeCodes);
    Page<Scope>getPage(ScopeCriteria criteria);
    Optional<Scope> getDetail(String scopeCode);
    Scope updateByCode(String scopeCode, Scope scope);
    Scope activeByCode(String scopeCode);
    Scope inactiveByCode(String scopeCode);
    void removeListByCodeIn(List<String> scopeCodes);
}
