package com.example.springsecurity.domain.user.exception;

import com.example.springsecurity.global.error.exception.ErrorCode;
import com.example.springsecurity.global.error.exception.SpringSecurityException;

public class ExpiredTokenException extends SpringSecurityException {
    public static final SpringSecurityException EXCEPTION = new ExpiredTokenException();

    private ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }

}
