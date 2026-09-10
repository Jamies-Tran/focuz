package com.focuz.authservice.infrastructure.persistence.cache.entity.authentication.code;

import com.focuz.authservice.infrastructure.persistence.cache.entity.user.UserEntity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.util.concurrent.TimeUnit;

@Getter
@Setter
@RedisHash("authenticationCode")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationCodeEntity {
        @Id
        String id;
        @Indexed
        String authenticationCode;
        UserEntity user;
        @TimeToLive(unit = TimeUnit.MILLISECONDS)
        Long ttl;
}
