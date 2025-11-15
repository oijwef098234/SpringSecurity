package com.example.springsecurity.domain.user.exception;

import com.example.springsecurity.global.error.exception.ErrorCode;
import com.example.springsecurity.global.error.exception.SpringSecurityException;

public class NotMatchedPasswordException extends SpringSecurityException {
    public static final SpringSecurityException EXCEPTION = new NotMatchedPasswordException();

    private NotMatchedPasswordException() {
        super(ErrorCode.NOT_MATCH_PASSWORD);
    }

}
