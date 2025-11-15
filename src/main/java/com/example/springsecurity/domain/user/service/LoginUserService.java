package com.example.springsecurity.domain.user.service;

import com.example.springsecurity.domain.admin.exception.NotMatchedUserException;
import com.example.springsecurity.domain.user.dto.LoginRequest;
import com.example.springsecurity.domain.user.dto.TokenResponse;
import com.example.springsecurity.domain.user.entity.User;
import com.example.springsecurity.domain.user.repository.UserRepository;
import com.example.springsecurity.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public TokenResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("아이디 또는 비밀번호를 확인해주세요."));

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw NotMatchedUserException.EXCEPTION;
        }
            return jwtTokenProvider.receiveToken(loginRequest.getUsername());
        }
}
