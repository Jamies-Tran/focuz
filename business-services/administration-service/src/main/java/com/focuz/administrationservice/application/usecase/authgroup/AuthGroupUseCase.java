package com.focuz.administrationservice.application.usecase.authgroup;

import com.focuz.administrationservice.domain.constant.enums.authgroup.EAuthGroupStatus;
import com.focuz.administrationservice.domain.constant.enums.error.EAppError;
import com.focuz.administrationservice.domain.entity.authgroup.AuthGroup;
import com.focuz.administrationservice.domain.entity.authgroup.AuthGroupCriteria;
import com.focuz.administrationservice.domain.repository.authgroup.AuthGroupRepository;
import com.focuz.administrationservice.domain.service.authgroup.AuthGroupService;
import com.focuz.corestarter.domain.entity.exception.ApplicationException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthGroupUseCase implements AuthGroupService {
    AuthGroupRepository repository;

    @Override
    @Transactional
    public List<AuthGroup> createList(List<AuthGroup> authGroups) {
        validateCreateList(authGroups);
        return repository.saveAll(authGroups);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AuthGroup> getDetailByCode(String authGroupCode) {
        return repository.findByAuthGroupCode(authGroupCode);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AuthGroup> getPage(AuthGroupCriteria criteria) {
        return repository.findAll(criteria, criteria.pageRequest());
    }

    @Override
    @Transactional
    public AuthGroup updateByCode(String authGroupCode, AuthGroup authGroup) {
        validateUpdateByCode(authGroupCode, authGroup);
        return repository.updateByAuthGroupCode(authGroupCode, authGroup)
                .orElseThrow(() -> new ApplicationException(EAppError.AUTH_GROUP_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public AuthGroup activeByCode(String authGroupCode) {
        return repository.updateByAuthGroupCode(authGroupCode, EAuthGroupStatus.ACTIVE)
                .orElseThrow(() -> new ApplicationException(EAppError.AUTH_GROUP_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public AuthGroup inactiveByCode(String authGroupCode) {
        return repository.updateByAuthGroupCode(authGroupCode, EAuthGroupStatus.INACTIVE)
                .orElseThrow(() -> new ApplicationException(EAppError.AUTH_GROUP_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public void removeListByCodeIn(List<String> authGroupCode) {
        repository.deleteAllByAuthGroupCodeIn(authGroupCode);
    }

    private void validateCreateList(List<AuthGroup> authGroups) {
        List<String> authGroupCodes = authGroups.stream().map(AuthGroup::authGroupCode).filter(StringUtils::hasText).toList();
        if(new HashSet<>(authGroupCodes).size() != authGroups.stream().filter(a -> StringUtils.hasText(a.authGroupCode())).count()) {
            throw new ApplicationException(EAppError.AUTH_GROUP_DUPLICATE_IN_LIST, HttpStatus.BAD_REQUEST);
        }
        if(repository.existsByAuthGroupCodeIn(authGroupCodes)) {
            throw new ApplicationException(EAppError.AUTH_GROUP_DUPLICATE_IN_DB, HttpStatus.BAD_REQUEST);
        }
    }

    private void validateUpdateByCode(String authGroupCode, AuthGroup authGroup) {
        repository.findByAuthGroupCode(authGroupCode)
                .ifPresent(a -> {
                    if(!Objects.equals(a.authGroupCode(), authGroup.authGroupCode())
                            && repository.existsByAuthGroupCodeIn(List.of(authGroup.authGroupCode()))) {
                        throw new ApplicationException(EAppError.AUTH_GROUP_DUPLICATE_IN_DB, HttpStatus.BAD_REQUEST);
                    }
                });
    }
}
