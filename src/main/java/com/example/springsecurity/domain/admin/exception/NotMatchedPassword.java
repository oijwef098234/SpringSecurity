package com.example.springsecurity.domain.admin.exception;

import com.example.springsecurity.global.error.exception.ErrorCode;
import com.example.springsecurity.global.error.exception.SpringSecurityException;

public class NotMatchedPassword extends SpringSecurityException {
  public static final SpringSecurityException EXCEPTION = new NotMatchedPassword();

  public NotMatchedPassword() {
    super(ErrorCode.NOT_MATCH_PASSWORD);
  }
}
