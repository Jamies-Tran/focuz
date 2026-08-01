package com.focuz.administrationservice.infrastructure.persistence.database.entity.user;

import com.focuz.administrationservice.domain.constant.enums.user.EUserStatus;
import com.focuz.administrationservice.infrastructure.bootstrap.utils.StringConvertUtils;
import com.focuz.corestarter.infrastructure.persistence.database.entity.BaseAuditorEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.util.StringUtils;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity extends BaseAuditorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;
    String username;
    String password;
    String statusCode;
    String statusName;
    String search;

    @PrePersist
    private void prePersist() {
        if (!StringUtils.hasText(statusCode)) {
            statusCode = EUserStatus.ACTIVE.getCode();
            statusName = EUserStatus.ACTIVE.getName();
        }
        search = StringConvertUtils.normalizeWhiteSpace("%s".formatted(username));
    }

    @PreUpdate
    private void preUpdate() {
        search = StringConvertUtils.normalizeWhiteSpace("%s".formatted(username));
    }
}
