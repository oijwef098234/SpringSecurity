package com.example.springsecurity.domain.admin.service;

import com.example.springsecurity.domain.admin.exception.AdminNotfoundException;
import com.example.springsecurity.domain.user.dto.TokenResponse;
import com.example.springsecurity.domain.user.dto.UserRequest;
import com.example.springsecurity.domain.user.entity.User;
import com.example.springsecurity.domain.user.repository.UserRepository;
import com.example.springsecurity.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminLoginService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenResponse login(UserRequest userRequest) {
        User user = userRepository.findByUsername(userRequest.getUsername())
                .orElseThrow(() -> AdminNotfoundException.EXCEPTION);

        return jwtTokenProvider.receiveToken(userRequest.getUsername());
    }

}
