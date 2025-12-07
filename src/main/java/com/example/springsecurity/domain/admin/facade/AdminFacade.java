package com.example.springsecurity.domain.admin.facade;

import com.example.springsecurity.domain.admin.exception.AdminNotfoundException;
import com.example.springsecurity.domain.user.entity.User;
import com.example.springsecurity.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminFacade {
    private final UserRepository userRepository;

    public User currentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> AdminNotfoundException.EXCEPTION);
    }
}
