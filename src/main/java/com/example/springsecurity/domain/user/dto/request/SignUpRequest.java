package com.example.springsecurity.domain.user.dto.request;

import lombok.Getter;

@Getter
public class SignUpRequest {
    private String username;
    private String password;
}
