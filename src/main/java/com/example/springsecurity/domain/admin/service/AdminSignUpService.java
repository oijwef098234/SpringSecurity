package com.example.springsecurity.domain.admin.service;

import com.example.springsecurity.domain.admin.exception.DuplicatedAdminException;
import com.example.springsecurity.domain.user.dto.UserRequest;
import com.example.springsecurity.domain.user.entity.User;
import com.example.springsecurity.domain.user.entity.enums.Roles;
import com.example.springsecurity.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminSignUpService {
    private final UserRepository userRepository;

    public void signUp(UserRequest userRequest) {
        if(userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
            throw DuplicatedAdminException.EXCEPTION;
        }
        User user  = User.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .role(Roles.ADMIN)
                .build();
        userRepository.save(user);
    }
}
