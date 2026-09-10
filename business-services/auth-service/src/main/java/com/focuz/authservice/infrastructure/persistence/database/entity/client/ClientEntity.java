package com.focuz.authservice.infrastructure.persistence.database.entity.client;

import com.focuz.authservice.domain.constant.enums.client.EClientStatus;
import com.focuz.corestarter.infrastructure.bootstrap.utils.StringConvertUtils;
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
    String clientSecret;
    String redirectUri;
    String statusCode;
    String statusName;
    String search;

    @PrePersist
    private void prePersistence() {
        clientSecret = UUID.randomUUID().toString();
        search = "%s;%s;%s"
                .formatted(
                        StringConvertUtils.normalizeWhiteSpace(clientCode),
                        StringConvertUtils.normalizeWhiteSpace(clientSecret),
                        StringConvertUtils.normalizeWhiteSpace(redirectUri)
                );
        if(!StringUtils.hasText(statusCode)) {
            statusCode = EClientStatus.ACTIVE.getCode();
            statusName = EClientStatus.ACTIVE.getName();
        }
    }

    @PreUpdate
    private void preUpdate() {
        search = "%s;%s;%s"
                .formatted(
                        StringConvertUtils.normalizeWhiteSpace(clientCode),
                        StringConvertUtils.normalizeWhiteSpace(clientSecret),
                        StringConvertUtils.normalizeWhiteSpace(redirectUri)
                );
    }
}
