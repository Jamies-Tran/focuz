package com.focuz.authservice.infrastructure.persistence.database.repository.application;

import com.focuz.authservice.domain.entity.application.AuthApplicationCriteria;
import com.focuz.authservice.infrastructure.persistence.database.entity.application.AuthApplicationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaAuthApplicationRepository extends JpaRepository<AuthApplicationEntity, Long> {
    Optional<AuthApplicationEntity> findByApplicationCode(String applicationCode);
    List<AuthApplicationEntity> findAllByApplicationCodeIn(List<String> applicationCodes);
    @Query("""
        SELECT a
        FROM AuthApplicationEntity a
        WHERE (:#{#criteria.search().empty} = TRUE
                OR a.search LIKE %:#{#criteria.search()}%)
        AND (:#{#criteria.authApplicationCodes().empty} = TRUE
                OR a.applicationCode IN :#{#criteria.authApplicationCodes()})
        AND (:#{#criteria.statusCodes().empty} = TRUE
                OR a.statusCode IN :#{#criteria.statusCodes()})
        """)
    Page<AuthApplicationEntity> findAll(AuthApplicationCriteria criteria, Pageable pageable);
    @Query("""
        SELECT a.applicationCode
        FROM AuthApplicationEntity a
        WHERE a.applicationCode IN :applicationCodes
        """)
    List<String> findAllApplicationCodeByApplicationCodeIn(List<String> applicationCodes);
}
