package com.focuz.authservice.infrastructure.persistence.database.repository.scope;

import com.focuz.authservice.domain.entity.scope.ScopeCriteria;
import com.focuz.authservice.infrastructure.persistence.database.entity.scope.ScopeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaScopeRepository extends JpaRepository<ScopeEntity, Long> {
    Optional<ScopeEntity> findByScopeCode(String scopeCode);
    List<ScopeEntity> findAllByScopeCodeIn(List<String> scopeCodes);
    Boolean existsAllByScopeCodeIn(List<String> scopeCodes);
    @Query("""
        SELECT s
        FROM ScopeEntity s
        WHERE (:#{#criteria.search().empty} = TRUE
                OR s.search LIKE %:#{#criteria.search()}%)
                AND (:#{#criteria.scopeCodes().empty} = TRUE
                        OR s.scopeCode IN :#{#criteria.scopeCodes()})
                AND (:#{#criteria.statusCodes().empty} = TRUE
                        OR s.statusCode IN :#{#criteria.statusCodes()})
        """)
    Page<ScopeEntity> findAll(ScopeCriteria criteria, Pageable pageable);
    @Query("""
        SELECT s.scopeId
        FROM ScopeEntity s
        WHERE s.scopeCode IN :scopeCodes
        """)
    List<Long> findAllScopeIdByScopeCodeIn(List<String> scopeCodes);
}
