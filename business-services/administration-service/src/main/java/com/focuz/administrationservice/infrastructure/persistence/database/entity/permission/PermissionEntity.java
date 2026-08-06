package com.focuz.administrationservice.infrastructure.persistence.database.entity.permission;

import com.focuz.administrationservice.domain.constant.enums.permission.EPermissionStatus;
import com.focuz.administrationservice.infrastructure.bootstrap.utils.StringConvertUtils;
import com.focuz.administrationservice.infrastructure.persistence.bootstrap.utils.CodeGenerateUtils;
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
@Table(name = "permissions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionEntity extends BaseAuditorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long permissionId;
    String permissionCode;
    String permissionName;
    String statusCode;
    String statusName;
    String search;

    @PrePersist
    private void prePersist() {
        if(!StringUtils.hasText(permissionCode)) {
            permissionCode = CodeGenerateUtils.generateCodeWithPrefix("PERMISSION");
        }
        if(!StringUtils.hasText(statusCode)) {
            statusCode = EPermissionStatus.ACTIVE.getCode();
            statusName = EPermissionStatus.ACTIVE.getName();
        }
        search = StringConvertUtils.normalizeWhiteSpace("%s;%s".formatted(permissionCode, permissionName));
    }

    @PreUpdate
    private void preUpdate() {
        search = StringConvertUtils.normalizeWhiteSpace("%s;%s".formatted(permissionCode, permissionName));
    }
}
