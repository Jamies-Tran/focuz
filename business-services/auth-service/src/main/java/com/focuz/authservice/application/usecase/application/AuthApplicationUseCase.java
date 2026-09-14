package com.focuz.authservice.application.usecase.application;

import com.focuz.authservice.domain.constant.enums.application.EAuthApplicationStatus;
import com.focuz.authservice.domain.constant.enums.error.EAppError;
import com.focuz.authservice.domain.entity.application.AuthApplication;
import com.focuz.authservice.domain.entity.application.AuthApplicationCriteria;
import com.focuz.authservice.domain.repository.application.AuthApplicationRepository;
import com.focuz.authservice.domain.service.application.AuthApplicationService;
import com.focuz.corestarter.domain.entity.exception.ApplicationException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthApplicationUseCase implements AuthApplicationService {
    AuthApplicationRepository repository;
    private final AuthApplicationRepository authApplicationRepository;

    @Override
    @Transactional
    public List<AuthApplication> createList(List<AuthApplication> authApplications) {
        validateCreateList(authApplications);
        return repository.saveAll(authApplications);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AuthApplication> getPage(AuthApplicationCriteria criteria) {
        return repository.findAll(criteria, criteria.pageRequest());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AuthApplication> getDetailByCode(String applicationCode) {
        return repository.findByApplicationCode(applicationCode);
    }

    @Override
    @Transactional
    public AuthApplication updateByCode(String applicationCode, AuthApplication application) {
        validateUpdateByCode(applicationCode, application);
        return repository.updateByApplicationCode(applicationCode, application)
                .orElseThrow(() -> new ApplicationException(EAppError.APPLICATION_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public AuthApplication activeByCode(String applicationCode) {
        return repository.updateByApplicationCode(applicationCode, EAuthApplicationStatus.ACTIVE)
                .orElseThrow(() -> new ApplicationException(EAppError.APPLICATION_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public AuthApplication inactiveByCode(String applicationCode) {
        return repository.updateByApplicationCode(applicationCode, EAuthApplicationStatus.INACTIVE)
                .orElseThrow(() -> new ApplicationException(EAppError.APPLICATION_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public void removeListByCodeIn(List<String> applicationCodes) {
        repository.deleteAllByApplicationCodeIn(applicationCodes);
    }

    private void validateCreateList(List<AuthApplication> authApplications) {
        Set<String> applicationCodes = new HashSet<>(
                authApplications.stream()
                        .map(AuthApplication::applicationCode)
                        .toList()
        );
        if(applicationCodes.size() != authApplications.size()) {
            throw new ApplicationException(EAppError.APPLICATION_DUPLICATED_IN_LIST, HttpStatus.BAD_REQUEST);
        }
        List<String> existedCodes = repository.findAllApplicationCodeByApplicationCodeIn(applicationCodes.stream().toList());
        if(!CollectionUtils.isEmpty(existedCodes)) {
            throw new ApplicationException(EAppError.APPLICATION_DUPLICATED_IN_DB ,  "Mã đã tồn tại %s".formatted(String.join(",", existedCodes)),
                    HttpStatus.BAD_REQUEST);
        }
    }

    private void validateUpdateByCode(String applicationCode, AuthApplication application) {
        repository.findByApplicationCode(applicationCode)
                .ifPresent(authApplication -> {
                    if(!Objects.equals(authApplication.applicationCode(), application.applicationCode())) {
                        throw new ApplicationException(EAppError.APPLICATION_DUPLICATED_IN_DB ,  "Mã đã tồn tại %s".formatted(application.applicationCode()),
                                HttpStatus.BAD_REQUEST);
                    }
                });
    }
}
