package com.focuz.authservice.infrastructure.persistence.database.adapter.application;

import com.focuz.authservice.domain.constant.enums.application.EAuthApplicationStatus;
import com.focuz.authservice.domain.entity.application.AuthApplication;
import com.focuz.authservice.domain.entity.application.AuthApplicationCriteria;
import com.focuz.authservice.domain.repository.application.AuthApplicationRepository;
import com.focuz.authservice.infrastructure.persistence.database.mapper.application.AuthApplicationEntityMapper;
import com.focuz.authservice.infrastructure.persistence.database.repository.application.JpaAuthApplicationRepository;
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
public class JpaAuthApplicationPersistenceAdapter implements AuthApplicationRepository {
    JpaAuthApplicationRepository repository;
    AuthApplicationEntityMapper mapper;

    @Override
    public List<AuthApplication> saveAll(List<AuthApplication> applications) {
        return mapper.toDomain(
                repository.saveAll(
                        mapper.toEntity(applications)
                )
        );
    }

    @Override
    public Page<AuthApplication> findAll(AuthApplicationCriteria criteria, PageRequest pageRequest) {
        return repository.findAll(criteria, pageRequest)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<AuthApplication> findByApplicationCode(String applicationCode) {
        return repository.findByApplicationCode(applicationCode)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<AuthApplication> updateByApplicationCode(String applicationCode, AuthApplication application) {
        return repository.findByApplicationCode(applicationCode)
                .map(a -> {
                    mapper.update(a, application);
                    return mapper.toDomain(
                            repository.save(a)
                    );
                });
    }

    @Override
    public Optional<AuthApplication> updateByApplicationCode(String applicationCode, EAuthApplicationStatus status) {
        return repository.findByApplicationCode(applicationCode)
                .map(a -> {
                    a.setStatusCode(status.getCode());
                    a.setStatusName(status.getName());
                    return mapper.toDomain(
                            repository.save(a)
                    );
                });
    }

    @Override
    public void deleteAllByApplicationCodeIn(List<String> applicationCodes) {
        repository.deleteAll(
                repository.findAllByApplicationCodeIn(applicationCodes)
        );
    }

    @Override
    public List<String> findAllApplicationCodeByApplicationCodeIn(List<String> applicationCodes) {
        return repository.findAllApplicationCodeByApplicationCodeIn(applicationCodes);
    }
}
