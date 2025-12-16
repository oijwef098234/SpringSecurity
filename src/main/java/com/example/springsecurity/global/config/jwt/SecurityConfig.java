package com.example.springsecurity.global.config.jwt;

import com.example.springsecurity.global.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // csrf 공격 방어
                // cors 설정은 프론트엔드가 없기때문에 필요 없음
                .headers(headers -> headers // 클릭 제킹 공격 방어
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .sessionManagement(session -> session // 세션 사용 설정
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 사용 안함
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/login", "/user/sign-up", "/user/change", "/user/reissue").permitAll() // 사용자 경로 권한 설정
                        .requestMatchers("/user/**").hasRole("USER")
                        .requestMatchers("/admin/sign-up", "/admin/login").permitAll() // 관리자 경로 권한 설정
                        .requestMatchers("/admin/**").hasRole("ADMIN")) // 관리자 경로 권한 설정
                .with(new SecurityFilterConfig(jwtTokenProvider, objectMapper), Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}