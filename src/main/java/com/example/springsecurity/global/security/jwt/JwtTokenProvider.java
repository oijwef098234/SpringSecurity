package com.example.springsecurity.global.security.jwt;

import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;

    public String createAccessToken(String password) {
        Date now = new Date(); // 현재 시간 기준으로 앞으로의 이 토큰이 얼마나 유효한지 알기 위해

        return
    }
}
