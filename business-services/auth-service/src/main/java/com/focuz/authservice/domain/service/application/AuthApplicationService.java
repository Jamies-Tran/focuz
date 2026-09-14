package com.focuz.authservice.domain.service.application;

import com.focuz.authservice.domain.entity.application.AuthApplication;
import com.focuz.authservice.domain.entity.application.AuthApplicationCriteria;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AuthApplicationService {
    List<AuthApplication> createList(List<AuthApplication> authApplications);
    Page<AuthApplication> getPage(AuthApplicationCriteria criteria);
    Optional<AuthApplication> getDetailByCode(String applicationCode);
    AuthApplication updateByCode(String applicationCode, AuthApplication application);
    AuthApplication activeByCode(String applicationCode);
    AuthApplication inactiveByCode(String applicationCode);
    void removeListByCodeIn(List<String> applicationCodes);
}
