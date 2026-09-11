package com.focuz.authservice.infrastructure.persistence.database.entity.scope;

import com.focuz.authservice.domain.constant.enums.scope.EScopeStatus;
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
@Table(name = "scopes")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScopeEntity extends BaseAuditorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long scopeId;
    String scopeCode;
    String scopeName;
    String statusCode;
    String statusName;
    String search;

    @PrePersist
    private void prePersist() {
        search = "%s;%s".formatted(
                StringConvertUtils.normalizeWhiteSpace(scopeCode),
                StringConvertUtils.normalizeWhiteSpace(scopeName)
        );
        if(!StringUtils.hasText(statusCode)) {
            statusCode = EScopeStatus.ACTIVE.name();
            statusName = EScopeStatus.ACTIVE.name();
        }
    }

    @PreUpdate
    private void preUpdate() {
        search = "%s;%s".formatted(
                StringConvertUtils.normalizeWhiteSpace(scopeCode),
                StringConvertUtils.normalizeWhiteSpace(scopeName)
        );
    }
}
