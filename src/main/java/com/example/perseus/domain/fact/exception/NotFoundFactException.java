package com.example.perseus.domain.fact.exception;

import com.example.perseus.global.error.exception.BaseException;
import com.example.perseus.global.error.exception.ErrorCode;

public class NotFoundFactException extends BaseException {
  public NotFoundFactException() {
    super(ErrorCode.FACT_NOT_FOUND);
  }

  static class Holder {
    private final static NotFoundFactException instance = new NotFoundFactException();
  }
  public static NotFoundFactException getInstance() {
    return Holder.instance;
  }
}
