package com.example.springsecurity.domain.admin.service;

import com.example.springsecurity.domain.admin.exception.AdminNotfoundException;
import com.example.springsecurity.domain.admin.exception.NotMatchedPassword;
import com.example.springsecurity.domain.user.dto.TokenResponse;
import com.example.springsecurity.domain.user.dto.UserRequest;
import com.example.springsecurity.domain.user.entity.User;
import com.example.springsecurity.domain.user.repository.UserRepository;
import com.example.springsecurity.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminLoginService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public TokenResponse login(UserRequest userRequest) {
        User user = userRepository.findByUsername(userRequest.getUsername())
                .orElseThrow(() -> AdminNotfoundException.EXCEPTION);

        if(!passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
            throw NotMatchedPassword.EXCEPTION;
        }

        return jwtTokenProvider.receiveToken(userRequest.getUsername());
    }

}
