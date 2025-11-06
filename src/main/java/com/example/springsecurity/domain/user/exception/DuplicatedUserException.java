package com.example.springsecurity.domain.user.exception;

import com.example.springsecurity.global.error.exception.ErrorCode;
import com.example.springsecurity.global.error.exception.SpringSecurityException;

public class DuplicatedUserException extends SpringSecurityException {
    public static final SpringSecurityException EXCEPTION = new DuplicatedUserException();

    public DuplicatedUserException() {
        super(ErrorCode.USER_DUPLICATE);
    }
}
