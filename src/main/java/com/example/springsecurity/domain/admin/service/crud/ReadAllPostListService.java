package com.example.springsecurity.domain.admin.service.crud;

import com.example.springsecurity.domain.user.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadAllPostListService {
    private final PostRepository postRepository;

    

}
