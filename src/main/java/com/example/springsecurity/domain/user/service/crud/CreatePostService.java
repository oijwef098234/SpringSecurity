package com.example.springsecurity.domain.user.service.crud;

import com.example.springsecurity.domain.user.dto.request.PostRequest;
import com.example.springsecurity.domain.user.entity.Post;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {
    public void createPost(PostRequest postRequest, Authentication authentication) {
        Post post = Post.builder()
                .title(postRequest.getTitle())
                .body(postRequest.getBody())
                .username(authentication.getName())
                .build();
    }
}
