package com.example.springsecurity.domain.user.exception;

import com.example.springsecurity.global.error.exception.ErrorCode;
import com.example.springsecurity.global.error.exception.SpringSecurityException;

public class InvalidTokenException extends SpringSecurityException {
  public static final SpringSecurityException EXCEPTION = new InvalidTokenException();

  private InvalidTokenException(){
    super(ErrorCode.INVALID_TOKEN);
  }
}
