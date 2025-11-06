package com.example.springsecurity.domain.user.controller;

import com.example.springsecurity.domain.user.dto.TokenResponse;
import com.example.springsecurity.domain.user.dto.UserRequest;
import com.example.springsecurity.domain.user.service.LoginUserService;
import com.example.springsecurity.domain.user.service.SignUpUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final LoginUserService loginUserService;
    private final SignUpUserService signUpUserService;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody UserRequest userRequest) {
        return loginUserService.login(userRequest);
    }
    @PostMapping("/sign-up")
    public void signUp(@RequestBody UserRequest userRequest) {
        signUpUserService.signUpUser(userRequest);
    }
}
