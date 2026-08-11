package com.focuz.administrationservice.infrastructure.persistence.database.repository.client;

import com.focuz.administrationservice.domain.entity.client.ClientCriteria;
import com.focuz.administrationservice.infrastructure.persistence.database.entity.client.ClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaClientRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findByClientCode(String clientCode);
    List<ClientEntity> findAllByClientCodeIn(List<String> clientCodes);
    @Query("""
        SELECT c
        FROM ClientEntity c
        WHERE (:#{#criteria.search().empty} = TRUE
                OR c.search LIKE %:#{#criteria.search()}%)
            AND (:#{#criteria.clientCodes().empty} = TRUE
                    OR c.statusCode IN :#{#criteria.clientCodes()})
            AND (:#{#criteria.statusCodes().empty} = TRUE
                    OR c.statusCode IN :#{#criteria.statusCodes()}) 
        """)
    Page<ClientEntity> findAll(ClientCriteria criteria, Pageable pageable);
}
