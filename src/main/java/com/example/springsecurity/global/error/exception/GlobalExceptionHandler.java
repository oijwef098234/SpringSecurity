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
                .body(new ErrorResponse(e.getErrorCode().getStatusCode(), e.getErrorCode().getErrorMessage()));
    }

    static class ErrorResponse {
        public final int code;
        public final String message;
        public ErrorResponse(int statusCode, String message) {
            this.code = statusCode;
            this.message = message;
        }
    }
}
