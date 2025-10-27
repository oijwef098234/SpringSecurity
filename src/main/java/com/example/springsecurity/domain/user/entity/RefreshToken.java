package com.example.springsecurity.domain.user.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash
@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {

    @Id
    private String userId;

    @Indexed
    private String token;

    @TimeToLive
    private Long timeToLive;
}
