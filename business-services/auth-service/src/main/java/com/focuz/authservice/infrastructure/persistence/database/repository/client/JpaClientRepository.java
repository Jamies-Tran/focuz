package com.focuz.authservice.infrastructure.persistence.database.repository.client;

import com.focuz.authservice.domain.entity.client.ClientCriteria;
import com.focuz.authservice.infrastructure.persistence.database.entity.client.ClientEntity;
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
    Optional<ClientEntity> findByClientSecret(String clientSecret);
    List<ClientEntity> findAllByClientCodeIn(List<String> clientCodes);
    @Query("""
        SELECT c
        FROM ClientEntity c
        WHERE (:#{#criteria.search().empty} = TRUE
                OR c.search LIKE %:#{#criteria.search()}%)
        AND (:#{#criteria.clientCodes().empty} = TRUE
                OR c.clientCode IN :#{#criteria.clientCodes()})
        AND (:#{#criteria.statusCodes().empty} = TRUE
                OR c.statusCode IN :#{#criteria.statusCodes()})
        """)
    Page<ClientEntity> findAll(ClientCriteria criteria, Pageable pageable);
}
