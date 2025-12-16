package com.example.springsecurity.domain.admin.dto.response;

import com.example.springsecurity.domain.user.entity.Post;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String body;
    private String username;

    public static PostResponse from(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .body(post.getBody())
                .username(post.getUsername())
                .build();
    }
}
