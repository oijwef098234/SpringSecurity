package com.example.springsecurity.domain.user.service;

import com.example.springsecurity.domain.user.dto.TokenResponse;
import com.example.springsecurity.domain.user.dto.UserRequest;
import com.example.springsecurity.domain.user.entity.User;
import com.example.springsecurity.domain.user.repository.UserRepository;
import com.example.springsecurity.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenResponse login(UserRequest userRequest) {
        User user = userRepository.findByUsername(userRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자 계정이 존재하지 않습니다."));

        return jwtTokenProvider.receiveToken(userRequest.getUsername());
    }
}
