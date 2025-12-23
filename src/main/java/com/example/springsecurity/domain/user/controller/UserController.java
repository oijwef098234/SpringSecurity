package com.example.springsecurity.domain.user.controller;

import com.example.springsecurity.domain.user.dto.request.ChangePasswordRequest;
import com.example.springsecurity.domain.user.dto.request.LoginRequest;
import com.example.springsecurity.domain.user.dto.response.TokenAndSessionResponse;
import com.example.springsecurity.domain.user.dto.response.TokenResponse;
import com.example.springsecurity.domain.user.dto.request.PostRequest;
import com.example.springsecurity.domain.user.dto.request.SignUpRequest;
import com.example.springsecurity.domain.user.service.auth.ChangeUserPasswordService;
import com.example.springsecurity.domain.user.service.auth.LoginUserService;
import com.example.springsecurity.domain.user.service.auth.ReissueService;
import com.example.springsecurity.domain.user.service.auth.SignUpUserService;
import com.example.springsecurity.domain.user.service.crud.CreatePostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final LoginUserService loginUserService;
    private final SignUpUserService signUpUserService;
    private final CreatePostService createPostService;
    private final ReissueService reissueService;
    private final ChangeUserPasswordService changeUserPasswordService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        TokenAndSessionResponse tokenAndSessionResponse = loginUserService.login(loginRequest);

        if(loginRequest.isRememberMe()) {
            ResponseCookie responseCookie = ResponseCookie.from("SESSION_ID", tokenAndSessionResponse.getSessionId())
                    .httpOnly(true)
                    .secure(false) // http통신에서만 사용, local에서는 false
                    .sameSite("Lax")
                    .path("/")
                    .maxAge(60 * 60 * 24 * 30)
                    .build();

            response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
        } else {
            ResponseCookie responseCookie = ResponseCookie.from("SESSION_ID", tokenAndSessionResponse.getSessionId())
                    .httpOnly(true)
                    .secure(true)
                    .sameSite("Lax")
                    .path("/")
                    .build();

            response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenAndSessionResponse.getToken())
                .build();
    }
    @PostMapping("/sign-up")
    public void signUp(@RequestBody SignUpRequest userRequest) {
        signUpUserService.signUpUser(userRequest);
    }

    @PostMapping("/change")
    public void changePassword(@RequestBody ChangePasswordRequest changePasswordRequest, Authentication authentication) {
        changeUserPasswordService.changeUserPassword(changePasswordRequest, authentication);
    }

    @PostMapping("/create")
    public void createPost(@RequestBody PostRequest postRequest, Authentication authentication) {
        createPostService.createPost(postRequest, authentication);
    }

    @PatchMapping("/reissue")
    public TokenResponse reissue(Authentication authentication, HttpServletRequest request) { // 만료시간이 다 되어갈때쯤 프론트에서 요청을 보내어 발급받는 reissue 과정
        return reissueService.reissue(authentication, request);
    }
//    public TokenResponse reissue(@RequestHeader("Refresh-Token") String refreshToken){ // 만료되었다는 에러를 클라가 잡아서 refreshToken을 header에 넣어서 reissue 요청을 보냄
//        return reissueService.reissue(refreshToken);
//    }
}