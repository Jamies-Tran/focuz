package com.focuz.administrationservice.infrastructure.persistence.database.repository.user;

import com.focuz.administrationservice.domain.entity.user.UserCriteria;
import com.focuz.administrationservice.infrastructure.persistence.database.entity.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    @Query("""
        SELECT u
        FROM UserEntity u
        INNER JOIN UserInfoEntity ui ON u.userId = ui.userId
        WHERE (:#{#criteria.search().empty} = TRUE
                    OR (u.search LIKE %:#{#criteria.search()}%
                            OR ui.search LIKE %:#{#criteria.search()}%))
                AND (:#{#criteria.dobRange().empty} = TRUE
                        OR ui.dob BETWEEN :#{#criteria.getFirstIndexDobRange()} AND :#{#criteria.getLastIndexDobRange()})
                AND (:#{#criteria.createdAtRange().empty} = TRUE
                        OR u.createdAt BETWEEN :#{#criteria.getFirstIndexCreatedAtRange()} AND :#{#criteria.getLastIndexCreatedAtRange()})
        """)
    Page<UserEntity> findAll(UserCriteria criteria, Pageable pageable);
    Boolean existsByUsername(String username);
}
