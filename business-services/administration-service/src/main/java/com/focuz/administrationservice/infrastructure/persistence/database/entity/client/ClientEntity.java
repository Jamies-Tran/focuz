package com.focuz.administrationservice.infrastructure.persistence.database.entity.client;

import com.focuz.administrationservice.domain.constant.enums.client.EClientStatus;
import com.focuz.administrationservice.infrastructure.bootstrap.utils.StringConvertUtils;
import com.focuz.administrationservice.infrastructure.persistence.bootstrap.utils.CodeGenerateUtils;
import com.focuz.corestarter.infrastructure.persistence.database.entity.BaseAuditorEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.util.StringUtils;

import java.util.UUID;

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
    String clientSecret;
    String statusCode;
    String statusName;
    String search;

    @PrePersist
    private void prePersist() {
        if(!StringUtils.hasText(statusCode)) {
            statusCode = EClientStatus.ACTIVE.getCode();
            statusName = EClientStatus.ACTIVE.getName();
        }
        if(!StringUtils.hasText(clientCode)) {
            clientCode = CodeGenerateUtils.generateCodeWithPrefix("CLIENT");
        }
        if(!StringUtils.hasText(clientSecret)) {
            clientSecret = UUID.randomUUID().toString();
        }
        search = StringConvertUtils.normalizeWhiteSpace("%s;%s".formatted(clientCode, clientName));
    }

    @PreUpdate
    private void preUpdate() {
        search = StringConvertUtils.normalizeWhiteSpace("%s;%s".formatted(clientCode, clientName));
    }
}
