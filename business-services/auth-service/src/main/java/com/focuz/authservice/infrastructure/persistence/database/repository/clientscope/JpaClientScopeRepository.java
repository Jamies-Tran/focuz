package com.focuz.authservice.infrastructure.persistence.database.repository.clientscope;

import com.focuz.authservice.domain.entity.clientscope.ClientScopeCriteria;
import com.focuz.authservice.infrastructure.persistence.database.entity.clientscope.ClientScopeDao;
import com.focuz.authservice.infrastructure.persistence.database.entity.clientscope.ClientScopeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaClientScopeRepository extends JpaRepository<ClientScopeEntity, Long> {
    List<ClientScopeEntity> findAllByClientId(Long clientId);
    @Query("""
        SELECT
                cs.clientScopeId AS clientScopeId,
                c.clientId AS clientId,
                s.scopeId AS scopeId,
                c.clientCode AS clientCode,
                c.clientSecret AS clientSecret,
                c.redirectUri AS redirectUri,
                s.scopeCode AS scopeCode,
                s.scopeName AS scopeName 
        FROM ClientScopeEntity cs
        INNER JOIN ClientEntity c ON cs.clientId = c.clientId
        INNER JOIN ScopeEntity s ON cs.scopeId = s.scopeId
        WHERE cs.clientId = :#{#criteria.clientId()}
            AND (:#{#criteria.search().empty} = TRUE
                    OR s.search LIKE %:#{#criteria.search()}%)
            AND (:#{#criteria.scopeCodes().empty} = TRUE
                    OR s.scopeCode IN :#{#criteria.scopeCodes()})
        """)
    Page<ClientScopeDao> findAll(ClientScopeCriteria criteria, Pageable pageable);
}
