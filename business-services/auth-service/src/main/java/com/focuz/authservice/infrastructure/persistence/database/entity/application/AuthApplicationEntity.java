package com.focuz.authservice.infrastructure.persistence.database.entity.application;

import com.focuz.authservice.domain.constant.enums.application.EAuthApplicationStatus;
import com.focuz.corestarter.infrastructure.bootstrap.utils.StringConvertUtils;
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
@Table(name = "applications")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthApplicationEntity extends BaseAuditorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long applicationId;
    String applicationCode;
    String applicationName;
    String statusCode;
    String statusName;
    String search;

    @PrePersist
    private void prePersist() {
        search = "%s;%s".formatted(
                StringConvertUtils.normalizeWhiteSpace(statusCode),
                StringConvertUtils.normalizeWhiteSpace(statusName)
        );
        if(!StringUtils.hasText(statusCode)) {
            statusCode = EAuthApplicationStatus.ACTIVE.getCode();
            statusName = EAuthApplicationStatus.ACTIVE.getName();
        }
    }

    @PreUpdate
    private void preUpdate() {
        search = "%s;%s".formatted(
                StringConvertUtils.normalizeWhiteSpace(statusCode),
                StringConvertUtils.normalizeWhiteSpace(statusName)
        );
    }
}
