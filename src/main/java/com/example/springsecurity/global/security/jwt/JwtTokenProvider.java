package com.example.springsecurity.global.security.jwt;

import com.example.springsecurity.domain.user.dto.response.TokenResponse;
import com.example.springsecurity.domain.user.entity.RefreshToken;
import com.example.springsecurity.domain.user.exception.ExpiredTokenException;
import com.example.springsecurity.domain.user.exception.InvalidTokenException;
import com.example.springsecurity.domain.user.exception.UserNotFoundException;
import com.example.springsecurity.domain.user.repository.RefreshTokenRepository;
import com.example.springsecurity.domain.user.repository.UserRepository;
import com.example.springsecurity.global.security.auth.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
                .setSubject(username)
                .claim("type", "refresh")
                .setIssuedAt(now)
                .setExpiration(new java.sql.Timestamp(now.getTime() + jwtProperties.getRefreshExpiration() * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .compact();

                refreshTokenRepository.save(
                        RefreshToken.builder()
                                .username(username)
                                .token(refreshToken)
                                .timeToLive(jwtProperties.getRefreshExpiration())
                                .build()
                );

                return refreshToken;
    }

    public String resolveToken(HttpServletRequest request) { // 토큰 형식확인, 토큰 추출
        String bearerToken = request.getHeader(jwtProperties.getHeader()); // 여기서 토큰 받아오기

        String prefix = jwtProperties.getPrefix(); // prefix 추출

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(prefix)) {
            return bearerToken.substring(prefix.length()).trim();
        }

        return null;
    }

    public Claims getClaim(String token) { // 토큰 검증후 claim객체 추출
        try{
            return Jwts
                    .parser() // 토큰을 분석하고 해석할 수 있는 객체 생성
                    .setSigningKey(jwtProperties.getSecret()) // 비밀키 받아와서 설정
                    .parseClaimsJws(token) // 서명이 유효한지 확인
                    .getBody(); // 만들어서 반환
        }
        catch (ExpiredJwtException e){ // 만료된 토큰
            throw ExpiredTokenException.EXCEPTION;
        }
        catch (Exception e){ // 검증되지 않은 토큰
            throw InvalidTokenException.EXCEPTION;
        }
    }
    public void validateToken(String token) { // 토큰 검증
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    public TokenResponse receiveToken(String username) { // 사용자 이름으로 토큰을 발급한다.
        Date now = new Date();

        userRepository.findByUsername(username)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return TokenResponse
                .builder()
                .accessToken(createAccessToken(username))
                .refreshToken(createRefreshToken(username))
                .accessExpiredAt(new Date(now.getTime() + jwtProperties.getAccessExpiration() * 1000))
                .refreshExpiredAt(new Date(now.getTime() + jwtProperties.getRefreshExpiration() * 1000))
                .build();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaim(token);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
