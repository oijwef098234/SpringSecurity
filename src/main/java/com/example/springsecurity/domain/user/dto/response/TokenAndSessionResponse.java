package com.example.springsecurity.domain.user.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TokenAndSessionResponse {
    private String token;
    private String sessionId;
}
