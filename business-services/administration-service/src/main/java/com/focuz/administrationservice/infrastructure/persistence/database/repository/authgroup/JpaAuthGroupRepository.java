package com.focuz.administrationservice.infrastructure.persistence.database.repository.authgroup;

import com.focuz.administrationservice.domain.entity.authgroup.AuthGroupCriteria;
import com.focuz.administrationservice.infrastructure.persistence.database.entity.authgroup.AuthGroupEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaAuthGroupRepository extends JpaRepository<AuthGroupEntity, Long> {
    Optional<AuthGroupEntity> findByAuthGroupCode(String authGroupCode);
    List<AuthGroupEntity> findAllByAuthGroupCodeIn(List<String> authGroupCodes);
    Boolean existsAllByAuthGroupCodeIn(List<String> authGroupCodes);
    @Query("""
        SELECT ag
        FROM AuthGroupEntity ag
        WHERE (:#{#criteria.search().empty} = TRUE
                OR ag.search LIKE %:#{#criteria.search()}%)
        AND (:#{#criteria.authGroupCodes().empty} = TRUE
                OR ag.authGroupCode IN :#{#criteria.authGroupCodes()})
        AND (:#{#criteria.statusCodes().empty} = TRUE
                OR ag.statusCode IN :#{#criteria.statusCodes()})
        """)
    Page<AuthGroupEntity> findAll(AuthGroupCriteria criteria, Pageable pageable);
}
