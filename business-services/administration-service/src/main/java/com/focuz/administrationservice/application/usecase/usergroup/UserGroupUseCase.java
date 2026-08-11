package com.focuz.administrationservice.application.usecase.usergroup;

import com.focuz.administrationservice.domain.entity.usergroup.UserGroup;
import com.focuz.administrationservice.domain.entity.usergroup.UserGroupCriteria;
import com.focuz.administrationservice.domain.repository.usergroup.UserGroupRepository;
import com.focuz.administrationservice.domain.service.usergroup.UserGroupService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserGroupUseCase implements UserGroupService {
    UserGroupRepository repository;

    @Override
    @Transactional
    public List<UserGroup> createList(Long authGroupId, List<Long> userIds) {
        return repository.saveAll(UserGroup.of(authGroupId, userIds));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserGroup> findAll(UserGroupCriteria criteria) {
        return repository.findAll(criteria, criteria.pageRequest());
    }
}
