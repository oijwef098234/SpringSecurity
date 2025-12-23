package com.example.springsecurity.domain.user.dto.request;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String username;
    private String password;
    private boolean rememberMe; // 자동로그인
}
