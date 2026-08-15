package com.focuz.administrationservice.infrastructure.persistence.database.repository.usergroup;

import com.focuz.administrationservice.domain.entity.usergroup.UserGroupCriteria;
import com.focuz.administrationservice.infrastructure.persistence.database.entity.usergroup.UserGroupDao;
import com.focuz.administrationservice.infrastructure.persistence.database.entity.usergroup.UserGroupEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaUserGroupRepository extends JpaRepository<UserGroupEntity, Long> {
    @Query("""
        SELECT DISTINCT ug.userGroupId
        FROM UserGroupEntity ug
        INNER JOIN AuthGroupEntity ag ON ag.authGroupId = ug.authGroupId
        INNER JOIN UserEntity u ON u.userId = ug.userId
        WHERE ag.authGroupCode = :autGroupCode
                AND u.userId IN :userIds
        """)
    List<Long> findAllUserGroupIdByAuthGroupCodeAndUserIdIn(String autGroupCode, List<Long> userIds);
    @Query("""
        SELECT 
                ug.userGroupId AS userGroupId,
                ug.userId AS userId,
                ug.authGroupId AS authGroupId,
                u.username AS username,
                u.avatar AS avatar,
               ui.mail AS mail,
               ui.phone AS phone
        FROM UserGroupEntity ug
        INNER JOIN UserEntity u ON ug.userId = u.userId
        INNER JOIN UserInfoEntity ui ON ui.userId = u.userId
        WHERE ug.authGroupId = :#{#criteria.authGroupId()}
            AND (:#{#criteria.search().empty} = TRUE
                    OR u.search LIKE :#{#criteria.search()})
        """)
    Page<UserGroupDao> findAll(UserGroupCriteria criteria, Pageable pageable);
}
