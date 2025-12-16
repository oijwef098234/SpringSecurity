package com.example.springsecurity.domain.admin.service.crud;

import com.example.springsecurity.domain.admin.dto.response.PostResponse;
import com.example.springsecurity.domain.user.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadAllPostListService {
    private final PostRepository postRepository;

    public List<PostResponse> readAllPostList() {
        return postRepository.findAll()
                .stream()
                .map(PostResponse::from)
                .toList();
    }
}
