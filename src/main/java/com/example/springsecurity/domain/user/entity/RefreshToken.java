package com.example.springsecurity.domain.user.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {

//    @Id // accessToken과 refreshToken을 둘 다 주는 방식에서 사용
//    private String username;
//
//    @Indexed
//    private String token;
//
//    @TimeToLive
//    private Long timeToLive;

    @Id // accessToken만 주는 방식에서 사용
    private String sessionId;

    @Indexed
    private String username;


    private String token;

    @TimeToLive
    private Long timeToLive;
}
