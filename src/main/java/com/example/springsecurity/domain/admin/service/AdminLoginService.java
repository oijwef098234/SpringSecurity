package com.example.springsecurity.domain.admin.service;

import com.example.springsecurity.domain.admin.dto.LoginRequest;
import com.example.springsecurity.domain.admin.entity.Admin;
import com.example.springsecurity.domain.admin.exception.AdminNotfoundException;
import com.example.springsecurity.domain.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminLoginService {
    private final AdminRepository adminRepository;

    public void login(LoginRequest loginRequest) {
        Admin admin = adminRepository.findByAdminId(loginRequest.getAdminId()).orElseThrow(
                () -> AdminNotfoundException.EXCEPTION
        );
    }
}
