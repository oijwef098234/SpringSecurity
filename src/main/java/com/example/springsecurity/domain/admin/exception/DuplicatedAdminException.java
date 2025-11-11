package com.example.springsecurity.domain.admin.exception;

import com.example.springsecurity.domain.user.exception.DuplicatedUsernameException;
import com.example.springsecurity.global.error.exception.ErrorCode;
import com.example.springsecurity.global.error.exception.SpringSecurityException;

public class DuplicatedAdminException extends SpringSecurityException{
    public static final SpringSecurityException EXCEPTION = new DuplicatedUsernameException();

    public DuplicatedAdminException() {
        super(ErrorCode.ADMIN_DUPLICATE);
    }
}
