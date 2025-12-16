package com.example.springsecurity.global.error;

import com.example.springsecurity.global.error.exception.ErrorCode;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ErrorResponse { // 에러 띄워주는 이쁜 틀
    private String message; // 에러 메시지
    private Integer status; // 에러 코드
    private LocalDateTime timestamp; // 에러 뜬 시간
    private String description; // 에러 설명

    public static ErrorResponse of(ErrorCode errorCode, String description) {
        return ErrorResponse.builder()
                .message(errorCode.getErrorMessage())
                .status(errorCode.getStatusCode())
                .timestamp(LocalDateTime.now())
                .description(description)
                .build();
    }

    public static ErrorResponse of(int statusCode, String description) {
        return ErrorResponse.builder()
                .message(description)
                .status(statusCode)
                .timestamp(LocalDateTime.now())
                .description(description)
                .build();
    }

}