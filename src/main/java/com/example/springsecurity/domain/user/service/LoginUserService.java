package com.example.springsecurity.domain.user.service;

import com.example.springsecurity.domain.admin.exception.NotMatchedUserException;
import com.example.springsecurity.domain.user.dto.LoginRequest;
import com.example.springsecurity.domain.user.dto.TokenResponse;
import com.example.springsecurity.domain.user.entity.RefreshToken;
import com.example.springsecurity.domain.user.entity.User;
import com.example.springsecurity.domain.user.repository.RefreshTokenRepository;
import com.example.springsecurity.domain.user.repository.UserRepository;
import com.example.springsecurity.global.security.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenResponse login(LoginRequest loginRequest, HttpServletResponse response) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> NotMatchedUserException.EXCEPTION);

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw NotMatchedUserException.EXCEPTION;
        }
        String accessToken = jwtTokenProvider.createAccessToken(user.getUsername());
        String refreshToken = null;

        if(loginRequest.isRememberMe()) {
            refreshToken = jwtTokenProvider.createRefreshToken(loginRequest.getUsername());

            refreshTokenRepository.save(
                    RefreshToken.builder()
                            .username(loginRequest.getUsername())
                            .token(accessToken)
                            .timeToLive(System.currentTimeMillis() + jwtTokenProvider.getRefreshExpiration())
                            .build()
            );

            addRefreshTokenCookie(response, refreshToken);
        }

            return jwtTokenProvider.receiveToken(loginRequest.getUsername());
        }

        public void addRefreshTokenCookie(HttpServletResponse response, String refreshToken) {
            String cookie = String.format(
                    "refreshToken=%s; Max-Age=%d; Path=/; HttpOnly; Secure; SameSite=Strict",
                    refreshToken,
                    60 * 60 * 24 * 7 // 7일
            );
            response.addHeader("Set-Cookie", cookie);
        }
}
