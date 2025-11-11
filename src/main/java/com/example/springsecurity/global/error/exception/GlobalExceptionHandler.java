package com.example.springsecurity.global.error.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SpringSecurityException.class)
    public ResponseEntity<?> handleSecurityException(SpringSecurityException e) {
        return ResponseEntity
                .status(e.getErrorCode().getStatusCode())  // 네 ErrorCode에 상태코드 있으면 그거 쓰고
                .body(new ErrorResponse(e.getErrorCode().name(), e.getMessage()));
    }

    static class ErrorResponse {
        public final String code;
        public final String message;
        public ErrorResponse(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
