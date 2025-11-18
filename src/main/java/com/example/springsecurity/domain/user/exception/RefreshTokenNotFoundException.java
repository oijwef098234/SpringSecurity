package com.example.springsecurity.domain.user.exception;

import com.example.springsecurity.global.error.exception.ErrorCode;
import com.example.springsecurity.global.error.exception.SpringSecurityException;

public class RefreshTokenNotFoundException extends SpringSecurityException {
  public static final SpringSecurityException EXCEPTION = new RefreshTokenNotFoundException();
  private RefreshTokenNotFoundException(){
    super(ErrorCode.NOT_FOUND_REFRESHTOKEN);
  }
}
