package com.focuz.administrationservice.infrastructure.persistence.database.entity.authgroup;

import com.focuz.administrationservice.domain.constant.enums.authgroup.EAuthGroupStatus;
import com.focuz.administrationservice.infrastructure.bootstrap.utils.StringConvertUtils;
import com.focuz.administrationservice.infrastructure.persistence.bootstrap.utils.CodeGenerateUtils;
import com.focuz.corestarter.infrastructure.persistence.database.entity.BaseAuditorEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "auth_groups")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthGroupEntity extends BaseAuditorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long authGroupId;
    String authGroupCode;
    String authGroupName;
    String statusCode;
    String statusName;
    String search;

    @PrePersist
    public void prePersist() {
        if(!StringUtils.hasText(authGroupCode)) {
            authGroupCode = CodeGenerateUtils.generateCodeWithPrefix("GROUP");
        }
        if(!StringUtils.hasText(statusCode)) {
            statusCode = EAuthGroupStatus.ACTIVE.getCode();
            statusName = EAuthGroupStatus.ACTIVE.getName();
        }
        search = StringConvertUtils.normalizeWhiteSpace("%s;%s".formatted(authGroupCode, authGroupName));
    }

    @PreUpdate
    public void preUpdate() {
        search = StringConvertUtils.normalizeWhiteSpace("%s;%s".formatted(authGroupCode, authGroupName));
    }
}
