package com.example.springsecurity.domain.user.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("refresh_tokens")   // Redis key-space 이름
@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class RefreshToken {

    @Id
    private String id;

    @Indexed
    private String subject;

    private String token;

    @TimeToLive
    private Long timeToLive;
}
