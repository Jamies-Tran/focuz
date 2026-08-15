package com.focuz.administrationservice.domain.repository.usergroup;

import com.focuz.administrationservice.domain.entity.usergroup.UserGroup;
import com.focuz.administrationservice.domain.entity.usergroup.UserGroupCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserGroupRepository {
    List<UserGroup> saveAll(List<UserGroup> userGroups);
    Page<UserGroup> findAll(UserGroupCriteria criteria, PageRequest pageRequest);
    List<Long> findAllUserGroupIdByAuthGroupCodeAndUserIdIn(String authGroupCode, List<Long> userIds);
    void deleteAllByUserGroupIdIn(List<Long> userGroupIds);
}
