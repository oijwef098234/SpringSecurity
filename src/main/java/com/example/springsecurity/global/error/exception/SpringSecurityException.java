package com.example.springsecurity.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SpringSecurityException extends RuntimeException {
    private final ErrorCode errorCode;

    public SpringSecurityException(ErrorCode errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }
}
