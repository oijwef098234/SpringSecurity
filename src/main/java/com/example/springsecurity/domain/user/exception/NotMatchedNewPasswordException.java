package com.example.springsecurity.domain.user.exception;

import com.example.springsecurity.global.error.exception.ErrorCode;
import com.example.springsecurity.global.error.exception.SpringSecurityException;

public class NotMatchedNewPasswordException extends SpringSecurityException {
    public static final SpringSecurityException EXCEPTION = new NotMatchedNewPasswordException();

    private NotMatchedNewPasswordException() {
        super(ErrorCode.NOT_MATCH_NEW_PASSWORD);
    }
}
