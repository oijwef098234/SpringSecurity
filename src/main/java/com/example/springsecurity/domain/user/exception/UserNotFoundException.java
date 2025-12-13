package com.example.springsecurity.domain.user.exception;

import com.example.springsecurity.global.error.exception.ErrorCode;
import com.example.springsecurity.global.error.exception.SpringSecurityException;

public class UserNotFoundException extends SpringSecurityException {
    public static final SpringSecurityException EXCEPTION = new UserNotFoundException();

    public UserNotFoundException() {
        super(ErrorCode.EMAIL_DUPLICATE);
    }
}
