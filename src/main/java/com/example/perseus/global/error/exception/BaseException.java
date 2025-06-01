package com.example.perseus.global.error.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{
  private ErrorCode errorCode;
}
