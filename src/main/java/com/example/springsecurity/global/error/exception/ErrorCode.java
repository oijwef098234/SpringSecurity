package com.example.springsecurity.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    // admin
    ADMIN_NOT_FOUND(404, "관리자 계정을 찾을 수 없습니다.");

    private final int statusCode;
    private final String errorMessage;

}
