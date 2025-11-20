package com.example.springsecurity.domain.admin.service.auth;

import com.example.springsecurity.domain.admin.exception.DuplicatedAdminException;
import com.example.springsecurity.domain.user.dto.SignUpRequest;
import com.example.springsecurity.domain.user.entity.User;
import com.example.springsecurity.domain.user.entity.enums.Roles;
import com.example.springsecurity.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminSignUpService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(SignUpRequest userRequest) {
        if(userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
            throw DuplicatedAdminException.EXCEPTION;
        }
        User user  = User.builder()
                .username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword())) // 비밀번호 암호화
                .role(Roles.ADMIN)
                .build();
        userRepository.save(user);
    }
}
