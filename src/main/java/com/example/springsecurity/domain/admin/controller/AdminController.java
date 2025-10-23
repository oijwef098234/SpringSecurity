package com.example.springsecurity.domain.admin.controller;

import com.example.springsecurity.domain.admin.dto.UserResponse;
import com.example.springsecurity.domain.admin.service.ReadAllUserListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ReadAllUserListService readAllUserListService;

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return readAllUserListService.findAllUser();
    }
}
