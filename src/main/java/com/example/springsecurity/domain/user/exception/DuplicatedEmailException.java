package com.example.springsecurity.domain.user.exception;

import com.example.springsecurity.global.error.exception.ErrorCode;
import com.example.springsecurity.global.error.exception.SpringSecurityException;

public class DuplicatedEmailException extends SpringSecurityException {
    public static final SpringSecurityException EXCEPTION = new DuplicatedEmailException();

    public DuplicatedEmailException() {
        super(ErrorCode.EMAIL_DUPLICATE);
    }
}
