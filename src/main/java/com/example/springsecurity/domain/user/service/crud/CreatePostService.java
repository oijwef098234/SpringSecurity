package com.example.springsecurity.domain.user.service.crud;

import com.example.springsecurity.domain.user.dto.request.PostRequest;
import com.example.springsecurity.domain.user.entity.Post;
import com.example.springsecurity.domain.user.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePostService {
    private final PostRepository postRepository;

    public void createPost(PostRequest postRequest, Authentication authentication) {
        Post post = Post.builder()
                .title(postRequest.getTitle())
                .body(postRequest.getBody())
                .username(authentication.getName())
                .build();

        postRepository.save(post);
    }
}
