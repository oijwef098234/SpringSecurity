package com.example.springsecurity.domain.admin.exception;

import com.example.springsecurity.global.error.exception.ErrorCode;
import com.example.springsecurity.global.error.exception.SpringSecurityException;

public class AdminNotfoundException extends SpringSecurityException {
    public static final AdminNotfoundException EXCEPTION = new AdminNotfoundException();

    public AdminNotfoundException() {
        super(ErrorCode.ADMIN_NOT_FOUND);
    }
}
