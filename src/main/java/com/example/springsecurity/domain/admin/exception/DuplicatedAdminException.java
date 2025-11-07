package com.example.springsecurity.domain.admin.exception;

import com.example.springsecurity.domain.user.exception.DuplicatedUserException;
import com.example.springsecurity.global.error.exception.ErrorCode;
import com.example.springsecurity.global.error.exception.SpringSecurityException;

public class DuplicatedAdminException extends SpringSecurityException{
    public static final SpringSecurityException EXCEPTION = new DuplicatedUserException();

    public DuplicatedAdminException() {
        super(ErrorCode.ADMIN_DUPLICATE);
    }
}
