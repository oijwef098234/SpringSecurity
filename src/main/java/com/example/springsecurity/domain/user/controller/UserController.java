package com.example.springsecurity.domain.user.controller;

import com.example.springsecurity.domain.user.dto.ChangePasswordRequest;
import com.example.springsecurity.domain.user.dto.LoginRequest;
import com.example.springsecurity.domain.user.dto.TokenResponse;
import com.example.springsecurity.domain.user.dto.SignUpRequest;
import com.example.springsecurity.domain.user.service.ChangeUserPasswordService;
import com.example.springsecurity.domain.user.service.LoginUserService;
import com.example.springsecurity.domain.user.service.SignUpUserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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
    private final ChangeUserPasswordService changeUserPasswordService;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest loginRequest,  HttpServletResponse response) {
        return loginUserService.login(loginRequest, response);
    }
    @PostMapping("/sign-up")
    public void signUp(@RequestBody SignUpRequest userRequest) {
        signUpUserService.signUpUser(userRequest);
    }

    @PostMapping("/change")
    public void changePassword(@RequestBody ChangePasswordRequest changePasswordRequest, Authentication authentication) {
        changeUserPasswordService.changeUserPassword(changePasswordRequest, authentication);
    }

}