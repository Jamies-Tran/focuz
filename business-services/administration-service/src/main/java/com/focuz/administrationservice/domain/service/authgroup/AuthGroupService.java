package com.focuz.administrationservice.domain.service.authgroup;

import com.focuz.administrationservice.domain.entity.authgroup.AuthGroup;
import com.focuz.administrationservice.domain.entity.authgroup.AuthGroupCriteria;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AuthGroupService {
    List<AuthGroup> createList(List<AuthGroup> authGroups);
    Optional<AuthGroup> getDetailByCode(String authGroupCode);
    Page<AuthGroup> getPage(AuthGroupCriteria criteria);
    AuthGroup updateByCode(String authGroupCode, AuthGroup authGroup);
    AuthGroup activeByCode(String authGroupCode);
    AuthGroup inactiveByCode(String authGroupCode);
    void removeListByCodeIn(List<String> authGroupCode);
}
