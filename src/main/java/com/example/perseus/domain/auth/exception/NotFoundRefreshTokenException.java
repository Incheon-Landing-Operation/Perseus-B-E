package com.example.perseus.domain.auth.exception;

import com.example.perseus.global.error.exception.BaseException;
import com.example.perseus.global.error.exception.ErrorCode;

public class NotFoundRefreshTokenException extends BaseException {
  public NotFoundRefreshTokenException() {
    super(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
  }

  static class Holder {
    private static final NotFoundRefreshTokenException instance = new NotFoundRefreshTokenException();
  }

  public static NotFoundRefreshTokenException getInstance() {
    return Holder.instance;
  }
}
