package com.focuz.administrationservice.infrastructure.persistence.database.adapter.authgroup;

import com.focuz.administrationservice.domain.constant.enums.authgroup.EAuthGroupStatus;
import com.focuz.administrationservice.domain.entity.authgroup.AuthGroup;
import com.focuz.administrationservice.domain.entity.authgroup.AuthGroupCriteria;
import com.focuz.administrationservice.domain.repository.authgroup.AuthGroupRepository;
import com.focuz.administrationservice.infrastructure.persistence.database.mapper.authgroup.AuthGroupEntityMapper;
import com.focuz.administrationservice.infrastructure.persistence.database.repository.authgroup.JpaAuthGroupRepository;
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
public class JpaAuthGroupPersistenceAdapter implements AuthGroupRepository {
    JpaAuthGroupRepository repository;
    AuthGroupEntityMapper mapper;

    @Override
    public List<AuthGroup> saveAll(List<AuthGroup> authGroups) {
        return mapper.toDomain(repository.saveAll(mapper.toEntity(authGroups)));
    }

    @Override
    public Optional<AuthGroup> findByAuthGroupCode(String authGroupCode) {
        return repository.findByAuthGroupCode(authGroupCode)
                .map(mapper::toDomain);
    }

    @Override
    public Page<AuthGroup> findAll(AuthGroupCriteria criteria, PageRequest pageRequest) {
        return repository.findAll(criteria, pageRequest)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<AuthGroup> updateByAuthGroupCode(String authGroupCode, AuthGroup authGroup) {
        return repository.findByAuthGroupCode(authGroupCode)
                .map(ag -> {
                    mapper.update(ag, authGroup);
                    return mapper.toDomain(repository.save(ag));
                });
    }

    @Override
    public Optional<AuthGroup> updateByAuthGroupCode(String authGroupCode, EAuthGroupStatus status) {
        return repository.findByAuthGroupCode(authGroupCode)
                .map(a -> {
                    a.setStatusCode(status.getCode());
                    a.setStatusName(status.getName());
                    return mapper.toDomain(repository.save(a));
                });
    }

    @Override
    public void deleteAllByAuthGroupCodeIn(List<String> authGroupCodes) {
        repository.deleteAll(repository.findAllByAuthGroupCodeIn(authGroupCodes));
    }

    @Override
    public Boolean existsByAuthGroupCodeIn(List<String> authGroupCodes) {
        return repository.existsAllByAuthGroupCodeIn(authGroupCodes);
    }
}
