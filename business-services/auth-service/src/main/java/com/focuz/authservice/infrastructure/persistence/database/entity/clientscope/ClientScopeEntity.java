package com.focuz.authservice.infrastructure.persistence.database.entity.clientscope;

import com.focuz.corestarter.infrastructure.persistence.database.entity.BaseAuditorEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client_scopes")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientScopeEntity extends BaseAuditorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long clientScopeId;
    Long clientId;
    Long scopeId;
}
