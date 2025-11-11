package com.example.springsecurity.domain.user.dto;

import lombok.Getter;

@Getter
public class SignUpRequest {
    private String username;
    private String password;
    private String email;
}
