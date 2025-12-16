package com.example.springsecurity.domain.admin.controller;

import com.example.springsecurity.domain.admin.dto.response.PostResponse;
import com.example.springsecurity.domain.admin.dto.response.UserResponse;
import com.example.springsecurity.domain.admin.service.crud.ReadAllPostListService;
import com.example.springsecurity.domain.admin.service.crud.ReadAllUserListService;
import com.example.springsecurity.domain.admin.service.auth.AdminLoginService;
import com.example.springsecurity.domain.admin.service.auth.AdminSignUpService;
import com.example.springsecurity.domain.user.dto.request.LoginRequest;
import com.example.springsecurity.domain.user.dto.response.TokenResponse;
import com.example.springsecurity.domain.user.dto.request.SignUpRequest;
import com.example.springsecurity.domain.user.service.auth.ReissueService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ReadAllUserListService readAllUserListService;
    private final AdminLoginService adminLoginService;
    private final ReissueService reissueService;
    private final AdminSignUpService adminSignUpService;
    private final ReadAllPostListService readAllPostListService;

    @GetMapping("/user")
    public List<UserResponse> getAllUsers() {
        return readAllUserListService.findAllUser();
    }
    @GetMapping("/post")
    public List<PostResponse> getAllPosts() {
        return readAllPostListService.readAllPostList();
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest loginRequest) {
        return adminLoginService.login(loginRequest);
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody SignUpRequest userRequest) {
        adminSignUpService.signUp(userRequest);
    }

    @PatchMapping("/reissue")
    public TokenResponse reissue(Authentication authentication, HttpServletRequest request) { // 만료시간이 다 되어갈때쯤 프론트에서 요청을 보내어 발급받는 reissue 과정
        return reissueService.reissue(authentication, request);
    }
//    public TokenResponse reissue(@RequestHeader("Refresh-Token") String refreshToken){ // 만료되었다는 에러를 클라가 잡아서 refreshToken을 header에 넣어서 reissue 요청을 보냄
//        return reissueService.reissue(refreshToken);
//    }
}
