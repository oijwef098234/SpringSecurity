package com.example.springsecurity.admin.service;

import com.example.springsecurity.admin.dto.UserResponse;
import com.example.springsecurity.user.repository.UserRepository;
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
