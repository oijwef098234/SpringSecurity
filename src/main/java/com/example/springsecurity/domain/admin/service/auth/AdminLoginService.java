package com.example.springsecurity.domain.admin.service.auth;

import com.example.springsecurity.domain.admin.exception.AdminNotfoundException;
import com.example.springsecurity.domain.admin.exception.NotMatchedUserException;
import com.example.springsecurity.domain.user.dto.request.LoginRequest;
import com.example.springsecurity.domain.user.dto.response.TokenAndSessionResponse;
import com.example.springsecurity.domain.user.dto.response.TokenResponse;
import com.example.springsecurity.domain.user.entity.RefreshToken;
import com.example.springsecurity.domain.user.entity.User;
import com.example.springsecurity.domain.user.repository.RefreshTokenRepository;
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
    private final RefreshTokenRepository refreshTokenRepository;

//    public TokenResponse login(LoginRequest loginRequest) { // accessToken과 refreshToken을 둘 다 주는 방식
//        User user = userRepository.findByUsername(loginRequest.getUsername())
//                .orElseThrow(() -> AdminNotfoundException.EXCEPTION);
//
//        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
//            throw NotMatchedUserException.EXCEPTION;
//        }
//
//        return jwtTokenProvider.receiveToken(loginRequest.getUsername());
//    }
    public TokenAndSessionResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> NotMatchedUserException.EXCEPTION);

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw NotMatchedUserException.EXCEPTION;
        }

        jwtTokenProvider.receiveToken(loginRequest.getUsername());

        RefreshToken refreshToken = refreshTokenRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> NotMatchedUserException.EXCEPTION);

        return TokenAndSessionResponse.builder()
                .token(jwtTokenProvider.receiveToken(loginRequest.getUsername()).getAccessToken())
                .sessionId(refreshToken.getSessionId())
                .build();
    }

}
