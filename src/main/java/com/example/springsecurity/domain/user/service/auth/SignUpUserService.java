package com.example.springsecurity.domain.user.service.auth;

import com.example.springsecurity.domain.user.dto.SignUpRequest;
import com.example.springsecurity.domain.user.entity.User;
import com.example.springsecurity.domain.user.entity.enums.Roles;
import com.example.springsecurity.domain.user.exception.DuplicatedUsernameException;
import com.example.springsecurity.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUpUser(SignUpRequest userRequest) { // 회원가입
        if(userRepository.findByUsername(userRequest.getUsername()).isPresent()) { // 사용자가 중복인지 확인
            throw DuplicatedUsernameException.EXCEPTION;
        }

        User user = User.builder() // 사용자 생성
                .username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword())) // 비밀번호 암호화
                .role(Roles.USER)
                .build();
        userRepository.save(user);
    }
}