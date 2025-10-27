package com.example.springsecurity.domain.admin.service;

import com.example.springsecurity.domain.admin.dto.UserResponse;
import com.example.springsecurity.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReadAllUserListService {
    private final UserRepository userRepository;

    public List<UserResponse> findAllUser() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }
}
