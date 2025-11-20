package com.example.springsecurity.domain.user.service.auth;

import com.example.springsecurity.domain.user.dto.request.ChangePasswordRequest;
import com.example.springsecurity.domain.user.entity.User;
import com.example.springsecurity.domain.user.exception.NotMatchedNewPasswordException;
import com.example.springsecurity.domain.user.exception.NotMatchedPasswordException;
import com.example.springsecurity.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChangeUserPasswordService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void changeUserPassword(ChangePasswordRequest changePasswordRequest, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 유저입니다."));

        if(!passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) { // 비밀번호 확인
            throw NotMatchedPasswordException.EXCEPTION;
        }
        if(!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmNewPassword())) { // 비밀번호 확인의 확인
            throw NotMatchedNewPasswordException.EXCEPTION;
        }

        user.changePassword(passwordEncoder.encode(changePasswordRequest.getNewPassword())); // 비밀번호 재설정
    }
}
