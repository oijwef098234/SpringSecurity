package com.example.springsecurity.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    // admin
    ADMIN_NOT_FOUND(404, "관리자 계정을 찾을 수 없습니다."),
    ADMIN_DUPLICATE(409, "이미 존재하는 관리자입니다."),

    //user
    USERNAME_DUPLICATE(409, "이미 존재하는 사용자입니다."),
    EMAIL_DUPLICATE(409, "이미 가입된 이메일입니다."),

    // Jwt
    INVALID_TOKEN(401, "검증 되지 않은 토큰 입니다."),
    NOT_MATCH_PASSWORD(400, "비밀번호가 일치하지 않습니다."),
    EXPIRED_TOKEN(404, "만료된 토큰입니다.");

    private final int statusCode;
    private final String errorMessage;
}
