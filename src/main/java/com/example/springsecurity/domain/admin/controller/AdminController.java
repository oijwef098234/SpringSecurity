package com.example.springsecurity.domain.admin.controller;

import com.example.springsecurity.domain.admin.dto.UserResponse;
import com.example.springsecurity.domain.admin.service.AdminLoginService;
import com.example.springsecurity.domain.admin.service.AdminSignUpService;
import com.example.springsecurity.domain.admin.service.ReadAllUserListService;
import com.example.springsecurity.domain.user.dto.TokenResponse;
import com.example.springsecurity.domain.user.dto.UserRequest;
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
    public TokenResponse login(@RequestBody UserRequest userRequest) {
        return adminLoginService.login(userRequest);
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody UserRequest userRequest) {
        adminSignUpService.signUp(userRequest);
    }
}
