package com.example.springsecurity.domain.admin.exception;

import com.example.springsecurity.global.error.exception.ErrorCode;
import com.example.springsecurity.global.error.exception.SpringSecurityException;

public class NotMatchedUserException extends SpringSecurityException {
  public static final SpringSecurityException EXCEPTION = new NotMatchedUserException();

  public NotMatchedUserException() {
    super(ErrorCode.NOT_MATCH_USER);
  }
}
