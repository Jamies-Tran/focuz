package com.focuz.authservice.infrastructure.persistence.database.adapter.client;

import com.focuz.authservice.domain.constant.enums.client.EClientStatus;
import com.focuz.authservice.domain.entity.client.Client;
import com.focuz.authservice.domain.entity.client.ClientCriteria;
import com.focuz.authservice.domain.repository.client.ClientRepository;
import com.focuz.authservice.infrastructure.persistence.database.mapper.client.ClientEntityMapper;
import com.focuz.authservice.infrastructure.persistence.database.repository.client.JpaClientRepository;
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
public class JpaClientPersistenceAdapter implements ClientRepository {
    JpaClientRepository repository;
    ClientEntityMapper entityMapper;

    @Override
    public List<Client> saveAll(List<Client> clients) {
        return entityMapper.toDomain(
                repository.saveAll(
                        entityMapper.toEntity(clients)
                )
        );
    }

    @Override
    public Optional<Client> findByClientCode(String clientCode) {
        return repository.findByClientCode(clientCode)
                .map(entityMapper::toDomain);
    }

    @Override
    public Optional<Client> findByClientSecret(String clientSecret) {
        return repository.findByClientSecret(clientSecret)
                .map(entityMapper::toDomain);
    }

    @Override
    public Page<Client> findAll(ClientCriteria criteria, PageRequest pageRequest) {
        return repository.findAll(criteria, pageRequest)
                .map(entityMapper::toDomain);
    }

    @Override
    public Optional<Client> updateByClientCode(String clientCode, Client client) {
        return repository.findByClientCode(clientCode)
                .map(c -> {
                    entityMapper.update(c, client);
                    return entityMapper.toDomain(
                            repository.save(c)
                    );
                });
    }

    @Override
    public Optional<Client> updateByClientCode(String clientCode, EClientStatus status) {
        return repository.findByClientCode(clientCode)
                .map(c -> {
                    c.setStatusCode(status.getCode());
                    c.setStatusName(status.getName());
                    return entityMapper.toDomain(
                            repository.save(c)
                    );
                });
    }

    @Override
    public void deleteAllByClientCodeIn(List<String> clientCodes) {
        repository.deleteAll(
                repository.findAllByClientCodeIn(clientCodes)
        );
    }
}
