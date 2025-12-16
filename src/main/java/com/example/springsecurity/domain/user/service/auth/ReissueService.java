package com.example.springsecurity.domain.user.service.auth;

import com.example.springsecurity.domain.user.dto.response.TokenResponse;
import com.example.springsecurity.domain.user.entity.RefreshToken;
import com.example.springsecurity.domain.user.exception.ExpiredTokenException;
import com.example.springsecurity.domain.user.repository.RefreshTokenRepository;
import com.example.springsecurity.global.security.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.Token;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReissueService {
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public TokenResponse reissue(Authentication authentication, HttpServletRequest request) { // 만료시간이 다 되어갈때쯤 프론트에서 요청을 보낼때 사용하는 reissue 방법
        jwtTokenProvider.validateToken(jwtTokenProvider.resolveToken(request));
        return jwtTokenProvider.receiveToken(authentication.getName());
    }
//
//    public TokenResponse reissue(String refreshToken) { // 만료가되었을때 프론트에서 잡아 refreshToken과 함께 reissue 요청을 보낼때 사용
//        jwtTokenProvider.validateToken(refreshToken); // 토큰 검증
//
//        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
//                .orElseThrow(() -> ExpiredTokenException.EXCEPTION);
//
//        String username = token.getUsername(); // username 저장
//
//        refreshTokenRepository.delete(token); // 기존 리프레시 토큰 삭제
//
//        return jwtTokenProvider.receiveToken(username);
//    }

}
