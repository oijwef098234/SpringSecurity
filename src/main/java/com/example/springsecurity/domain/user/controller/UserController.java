package com.example.springsecurity.domain.user.controller;

import com.example.springsecurity.domain.user.dto.ChangePasswordRequest;
import com.example.springsecurity.domain.user.dto.LoginRequest;
import com.example.springsecurity.domain.user.dto.TokenResponse;
import com.example.springsecurity.domain.user.dto.SignUpRequest;
import com.example.springsecurity.domain.user.entity.RefreshToken;
import com.example.springsecurity.domain.user.exception.InvalidTokenException;
import com.example.springsecurity.domain.user.exception.RefreshTokenNotFoundException;
import com.example.springsecurity.domain.user.repository.RefreshTokenRepository;
import com.example.springsecurity.domain.user.service.ChangeUserPasswordService;
import com.example.springsecurity.domain.user.service.LoginUserService;
import com.example.springsecurity.domain.user.service.SignUpUserService;
import com.example.springsecurity.global.security.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final LoginUserService loginUserService;
    private final JwtTokenProvider jwtTokenProvider;
    private final SignUpUserService signUpUserService;
    private final ChangeUserPasswordService changeUserPasswordService;
    private final RefreshTokenRepository refreshTokenRepository;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest loginRequest,  HttpServletResponse response) {
        return loginUserService.login(loginRequest, response);
    }
    @PostMapping("/sign-up")
    public void signUp(@RequestBody SignUpRequest userRequest) {
        signUpUserService.signUpUser(userRequest);
    }

    @PostMapping("/change")
    public void changePassword(@RequestBody ChangePasswordRequest changePasswordRequest, Authentication authentication) {
        changeUserPasswordService.changeUserPassword(changePasswordRequest, authentication);
    }

    @PostMapping("/token")
    public TokenResponse reissueToken(
            @CookieValue(value = "refreshToken", required = false) String refreshToken
    ) {
        if (refreshToken == null) {
            throw RefreshTokenNotFoundException.EXCEPTION;
        }

        String username = jwtTokenProvider.getUsernameFromToken(refreshToken);

        RefreshToken saved = refreshTokenRepository.findById(username) // 유저 있는지 확인
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        if (!saved.getToken().equals(refreshToken)) {
            throw InvalidTokenException.EXCEPTION;
        }

        String newAccessToken = jwtTokenProvider.createAccessToken(username);

        String newRefreshToken = jwtTokenProvider.createRefreshToken(username);

        return TokenResponse
                .builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

}