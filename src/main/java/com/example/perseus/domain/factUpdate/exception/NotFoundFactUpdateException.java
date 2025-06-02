package com.example.perseus.domain.factUpdate.exception;

import com.example.perseus.global.error.exception.BaseException;
import com.example.perseus.global.error.exception.ErrorCode;

public class NotFoundFactUpdateException extends BaseException {
  public NotFoundFactUpdateException() {
    super(ErrorCode.FACT_UPDATE_NOT_FOUND);
  }
  static class Holder {
    private final static NotFoundFactUpdateException instance = new NotFoundFactUpdateException();
  }
  public static NotFoundFactUpdateException getInstance() {
    return Holder.instance;
  }
}
