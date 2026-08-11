package com.focuz.administrationservice.infrastructure.persistence.database.entity.usergroup;

public interface UserGroupDao {
    Long getUserGroupId();
    Long getUserId();
    Long getAuthGroupId();
    String getUsername();
    String getAvatar();
    String getMail();
    String getPhone();
}
