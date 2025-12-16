package com.example.springsecurity.domain.admin.service.crud;

import com.example.springsecurity.domain.admin.dto.response.UserResponse;
import com.example.springsecurity.domain.admin.facade.AdminFacade;
import com.example.springsecurity.domain.user.entity.enums.Roles;
import com.example.springsecurity.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReadAllUserListService {
    private final UserRepository userRepository;
    private final AdminFacade adminFacade;

    public List<UserResponse> findAllUser() {
        adminFacade.currentUser();

        return userRepository.findAllByRole(Roles.USER)
                .stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }
}
