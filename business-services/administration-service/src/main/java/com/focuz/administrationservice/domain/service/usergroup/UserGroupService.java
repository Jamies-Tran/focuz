package com.focuz.administrationservice.domain.service.usergroup;

import com.focuz.administrationservice.domain.entity.usergroup.UserGroup;
import com.focuz.administrationservice.domain.entity.usergroup.UserGroupCriteria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserGroupService {
    List<UserGroup> createList(Long authGroupId, List<Long> userIds);
    Page<UserGroup> findAll(UserGroupCriteria criteria);
}
