package com.example.springsecurity.domain.user.service.auth;

import com.example.springsecurity.domain.user.dto.response.TokenResponse;
import com.example.springsecurity.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReissueService {
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse reissue(Authentication authentication) {
        return jwtTokenProvider.receiveToken(authentication.getName());
    }
}
