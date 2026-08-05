package com.focuz.administrationservice.domain.repository.authgroup;

import com.focuz.administrationservice.domain.constant.enums.authgroup.EAuthGroupStatus;
import com.focuz.administrationservice.domain.entity.authgroup.AuthGroup;
import com.focuz.administrationservice.domain.entity.authgroup.AuthGroupCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface AuthGroupRepository  {
    List<AuthGroup> saveAll(List<AuthGroup> authGroups);
    Optional<AuthGroup> findByAuthGroupCode(String authGroupCode);
    Page<AuthGroup> findAll(AuthGroupCriteria criteria, PageRequest pageRequest);
    Optional<AuthGroup> updateByAuthGroupCode(String authGroupCode, AuthGroup authGroup);
    Optional<AuthGroup> updateByAuthGroupCode(String authGroupCode, EAuthGroupStatus status);
    void deleteAllByAuthGroupCodeIn(List<String> authGroupCodes);
    Boolean existsByAuthGroupCodeIn(List<String> authGroupCodes);
}
