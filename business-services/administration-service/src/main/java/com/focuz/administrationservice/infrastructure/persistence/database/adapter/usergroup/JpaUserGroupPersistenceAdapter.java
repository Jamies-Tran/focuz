package com.focuz.administrationservice.infrastructure.persistence.database.adapter.usergroup;

import com.focuz.administrationservice.domain.entity.usergroup.UserGroup;
import com.focuz.administrationservice.domain.entity.usergroup.UserGroupCriteria;
import com.focuz.administrationservice.domain.repository.usergroup.UserGroupRepository;
import com.focuz.administrationservice.infrastructure.persistence.database.mapper.usergroup.UserGroupDaoMapper;
import com.focuz.administrationservice.infrastructure.persistence.database.mapper.usergroup.UserGroupEntityMapper;
import com.focuz.administrationservice.infrastructure.persistence.database.repository.usergroup.JpaUserGroupRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JpaUserGroupPersistenceAdapter implements UserGroupRepository {
    JpaUserGroupRepository repository;
    UserGroupEntityMapper mapper;
    UserGroupDaoMapper daoMapper;

    @Override
    public List<UserGroup> saveAll(List<UserGroup> userGroups) {
        return mapper.toDomain(repository.saveAll(mapper.toEntity(userGroups)));
    }

    @Override
    public Page<UserGroup> findAll(UserGroupCriteria criteria, PageRequest pageRequest) {
        return repository.findAll(criteria, pageRequest)
                .map(daoMapper::toDomain);
    }

    @Override
    public List<Long> findAllUserGroupIdByAuthGroupCodeAndUserIdIn(String authGroupCode, List<Long> userIds) {
        return repository.findAllUserGroupIdByAuthGroupCodeAndUserIdIn(authGroupCode, userIds);
    }

    @Override
    public void deleteAllByUserGroupIdIn(List<Long> userGroupIds) {
        repository.deleteAllById(userGroupIds);
    }
}
