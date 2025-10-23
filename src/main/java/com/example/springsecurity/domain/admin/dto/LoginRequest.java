package com.example.springsecurity.domain.admin.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String adminId;
    private String username;
    private String password;
}
