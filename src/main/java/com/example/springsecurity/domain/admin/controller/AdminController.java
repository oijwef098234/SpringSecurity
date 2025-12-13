package com.example.springsecurity.domain.admin.controller;

import com.example.springsecurity.domain.admin.dto.UserResponse;
import com.example.springsecurity.domain.admin.service.crud.ReadAllUserListService;
import com.example.springsecurity.domain.admin.service.auth.AdminLoginService;
import com.example.springsecurity.domain.admin.service.auth.AdminSignUpService;
import com.example.springsecurity.domain.user.dto.request.LoginRequest;
import com.example.springsecurity.domain.user.dto.response.TokenResponse;
import com.example.springsecurity.domain.user.dto.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ReadAllUserListService readAllUserListService;
    private final AdminLoginService adminLoginService;
    private final AdminSignUpService adminSignUpService;

    @GetMapping("/all")
    public List<UserResponse> getAllUsers() {
        return readAllUserListService.findAllUser();
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest loginRequest) {
        return adminLoginService.login(loginRequest);
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody SignUpRequest userRequest) {
        adminSignUpService.signUp(userRequest);
    }
}
