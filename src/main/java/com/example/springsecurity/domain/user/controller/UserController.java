package com.example.springsecurity.domain.user.controller;

import com.example.springsecurity.domain.user.dto.request.ChangePasswordRequest;
import com.example.springsecurity.domain.user.dto.request.LoginRequest;
import com.example.springsecurity.domain.user.dto.response.TokenResponse;
import com.example.springsecurity.domain.user.dto.request.PostRequest;
import com.example.springsecurity.domain.user.dto.request.SignUpRequest;
import com.example.springsecurity.domain.user.service.auth.ChangeUserPasswordService;
import com.example.springsecurity.domain.user.service.auth.LoginUserService;
import com.example.springsecurity.domain.user.service.auth.ReissueService;
import com.example.springsecurity.domain.user.service.auth.SignUpUserService;
import com.example.springsecurity.domain.user.service.crud.CreatePostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    public TokenResponse login(@RequestBody LoginRequest loginRequest) {
        return loginUserService.login(loginRequest);
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
    public TokenResponse reissue(Authentication authentication) {
        return reissueService.reissue(authentication);
    }

}