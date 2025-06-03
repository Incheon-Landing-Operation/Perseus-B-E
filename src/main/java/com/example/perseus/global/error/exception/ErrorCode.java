package com.example.perseus.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
  REFRESH_TOKEN_NOT_FOUND(404, "refresh token not found"),
  USER_NOT_FOUND(404, "user not found"),
  FACT_NOT_FOUND(404, "fact not found");

  private final int status;
  private final String message;
}
