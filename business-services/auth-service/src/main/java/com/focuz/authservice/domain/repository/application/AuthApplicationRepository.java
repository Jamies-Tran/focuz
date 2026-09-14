package com.focuz.authservice.domain.repository.application;

import com.focuz.authservice.domain.constant.enums.application.EAuthApplicationStatus;
import com.focuz.authservice.domain.entity.application.AuthApplication;
import com.focuz.authservice.domain.entity.application.AuthApplicationCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface AuthApplicationRepository {
    List<AuthApplication> saveAll(List<AuthApplication> applications);
    Page<AuthApplication> findAll(AuthApplicationCriteria criteria, PageRequest pageRequest);
    Optional<AuthApplication> findByApplicationCode(String applicationCode);
    Optional<AuthApplication> updateByApplicationCode(String applicationCode, AuthApplication application);
    Optional<AuthApplication> updateByApplicationCode(String applicationCode, EAuthApplicationStatus status);
    void deleteAllByApplicationCodeIn(List<String> applicationCodes);
    List<String> findAllApplicationCodeByApplicationCodeIn(List<String> applicationCodes);
}
