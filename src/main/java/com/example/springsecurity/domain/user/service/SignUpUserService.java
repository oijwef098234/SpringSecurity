package com.example.springsecurity.domain.user.service;

import com.example.springsecurity.domain.user.dto.UserRequest;
import com.example.springsecurity.domain.user.entity.User;
import com.example.springsecurity.domain.user.exception.DuplicatedUserException;
import com.example.springsecurity.domain.user.repository.UserRepository;
import com.example.springsecurity.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpUserService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public void signUpUser(UserRequest userRequest) { // 회원가입
        if(userRepository.findByUsername(userRequest.getUsername()).isPresent()) { // 사용자가 중복인지 확인
            throw DuplicatedUserException.EXCEPTION;
        }

        User user = User.builder() // 사용자 생성
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .build();
        userRepository.save(user);
    }
}