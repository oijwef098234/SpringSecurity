package com.example.springsecurity.domain.user.dto;

import lombok.Getter;

@Getter
public class UserRequest {
    private String username;
    private String password;
}
