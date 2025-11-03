package com.example.springsecurity.domain.user.service;

import com.example.springsecurity.domain.user.repository.UserRepository;
import com.example.springsecurity.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    
}
