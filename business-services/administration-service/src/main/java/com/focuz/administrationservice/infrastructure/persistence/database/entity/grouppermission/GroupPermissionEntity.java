package com.focuz.administrationservice.infrastructure.persistence.database.entity.grouppermission;

import com.focuz.corestarter.infrastructure.persistence.database.entity.BaseAuditorEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "group_permission")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupPermissionEntity extends BaseAuditorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long groupPermissionId;
    @Column(name = "group_id")
    Long authGroupId;
    Long permissionId;
}
