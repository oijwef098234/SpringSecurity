package com.example.springsecurity.global.security.jwt;

import com.example.springsecurity.domain.user.dto.TokenResponse;
import com.example.springsecurity.domain.user.entity.RefreshToken;
import com.example.springsecurity.domain.user.entity.User;
import com.example.springsecurity.domain.user.exception.ExpiredTokenException;
import com.example.springsecurity.domain.user.exception.InvalidTokenException;
import com.example.springsecurity.domain.user.repository.RefreshTokenRepository;
import com.example.springsecurity.domain.user.repository.UserRepository;
import com.example.springsecurity.global.security.auth.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final CustomUserDetailsService customUserDetailsService;

    public String createAccessToken(String username) {
        Date now = new Date(); // 현재 시간 기준으로 앞으로의 이 토큰이 얼마나 유효한지 알기 위해

        return Jwts.builder()
                .setSubject(username) // 해당 토큰 소유주
                .claim("type", "access") // 해당 토큰 타입
                .setIssuedAt(now) // 해당 토큰 생성 시간
                .setExpiration(new Date(now.getTime() + jwtProperties.getAccessExpiration() * 1000)) // 해당 토큰 만료기한 설정
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret()) // 해당 토큰 알고리즘 방식, secret키 적용
                .compact(); // 끝
    }

    public String createRefreshToken(String username) { // 리프레쉬 토큰 발급
        Date now = new Date();

        String refreshToken = Jwts.builder()
                .claim("type", "refresh")
                .setIssuedAt(now)
                .setExpiration(new java.sql.Timestamp(now.getTime() + jwtProperties.getRefreshExpiration() * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getHeader())
                .compact();

                refreshTokenRepository.save(
                        RefreshToken.builder()
                                .userId(username)
                                .token(refreshToken)
                                .build()
                );

                return refreshToken;
    }

    public String resolveToken(HttpServletRequest request) { // 토큰 형식확인, 토큰 추출
        String bearerToken = request.getHeader(jwtProperties.getHeader()); // 여기서 토큰 받아오기

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtProperties.getPrefix()) // 여기서 형식 검증
                && bearerToken.length() > jwtProperties.getPrefix().length() + 1) {
            return bearerToken.substring(7); // 여기서 뒤에 7자리 부터 뽑아서 내보내기
        }

        return null;
    }

    public Claims getClaim(String token) { // 토큰 검증
        try{
            return Jwts
                    .parser() // 토큰을 분석하고 해석할 수 있는 객체 생성
                    .setSigningKey(jwtProperties.getSecret()) // 비밀키 받아와서 설정
                    .parseClaimsJws(token) // 서명이 유효한지 확인
                    .getBody(); // 만들어서 반환
        }
        catch (ExpiredTokenException expiredTokenException){ // 만료된 토큰
            throw ExpiredTokenException.EXCEPTION;
        }
        catch (Exception e){ // 검증되지 않은 토큰
            throw InvalidTokenException.EXCEPTION;
        }
    }

    public TokenResponse receiveToken(String username) { // 사용자 이름으로 토큰을 발급함
        Date now = new Date();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자 계정이 존재하지 않습니다."));

        return TokenResponse
                .builder()
                .accessToken(createAccessToken(username))
                .refreshToken(createRefreshToken(username))
                .accessExpiredAt(new Date(now.getTime() + jwtProperties.getAccessExpiration()))
                .refreshExpiredAt(new Date(now.getTime() + jwtProperties.getRefreshExpiration()))
                .build();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaim(token);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
