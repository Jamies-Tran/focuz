package com.focuz.administrationservice.infrastructure.persistence.database.entity.client;

import com.focuz.administrationservice.domain.constant.enums.client.EClientStatus;
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
@Table(name = "clients")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientEntity extends BaseAuditorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long clientId;
    String clientCode;
    String clientName;
    String statusCode;
    String statusName;
    String search;

    @PrePersist
    private void prePersist() {
        search = StringConvertUtils.normalizeWhiteSpace("%s;%s".formatted(clientCode, clientName));
        if(!StringUtils.hasText(statusCode)) {
            statusCode = EClientStatus.ACTIVE.getCode();
            statusName = EClientStatus.ACTIVE.getName();
        }
    }

    @PreUpdate
    private void preUpdate() {
        search = StringConvertUtils.normalizeWhiteSpace("%s;%s".formatted(clientCode, clientName));
    }
}
