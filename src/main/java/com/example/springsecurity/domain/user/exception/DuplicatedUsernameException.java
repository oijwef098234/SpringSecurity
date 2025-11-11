package com.example.springsecurity.domain.user.exception;

import com.example.springsecurity.global.error.exception.ErrorCode;
import com.example.springsecurity.global.error.exception.SpringSecurityException;

public class DuplicatedUsernameException extends SpringSecurityException {
    public static final SpringSecurityException EXCEPTION = new DuplicatedUsernameException();

    public DuplicatedUsernameException() {
        super(ErrorCode.USERNAME_DUPLICATE);
    }
}
