package com.focuz.administrationservice.infrastructure.persistence.database.entity.userinfo;

import com.focuz.administrationservice.infrastructure.bootstrap.utils.StringConvertUtils;
import com.focuz.corestarter.infrastructure.persistence.database.entity.BaseAuditorEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_infos")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserInfoEntity extends BaseAuditorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userInfoId;
    Long userId;
    String firstName;
    String lastName;
    String phone;
    String mail;
    String address;
    LocalDate dob;
    String search;

    @PrePersist
    private void prePersist() {
        String fullName = "%s%s".formatted(firstName, lastName);
        search = StringConvertUtils.normalizeWhiteSpace(
                "%s;%s;%s;%s;%s;%s".formatted(
                        firstName, lastName, fullName, phone, mail, address
                )
        );
    }

    @PreUpdate
    private void preUpdate() {
        String fullName = "%s%s".formatted(firstName, lastName);
        search = StringConvertUtils.normalizeWhiteSpace(
                "%s;%s;%s;%s;%s;%s".formatted(
                        firstName, lastName, fullName, phone, mail, address
                )
        );
    }

}
