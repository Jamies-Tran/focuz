package com.focuz.administrationservice.infrastructure.persistence.database.entity.usergroup;

import com.focuz.corestarter.infrastructure.persistence.database.entity.BaseAuditorEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_group")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserGroupEntity extends BaseAuditorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userGroupId;
    Long userId;
    @Column(name = "group_id")
    Long authGroupId;
}
