package com.example.perseus.domain.user.exception;

import com.example.perseus.global.error.exception.BaseException;
import com.example.perseus.global.error.exception.ErrorCode;

public class NotFoundUserException extends BaseException {

  public NotFoundUserException() {
    super(ErrorCode.USER_NOT_FOUND);
  }
  static class Holder {
    private final static NotFoundUserException instance = new NotFoundUserException();
  }
  public static NotFoundUserException getInstance() {
    return Holder.instance;
  }
}
