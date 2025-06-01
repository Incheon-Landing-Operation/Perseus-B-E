package com.example.perseus.global.util;

import com.example.perseus.global.dto.ResponseDto;

public final class ApiUtil {

  public static <T> ResponseDto<T> success(int status, String message, T data) {
    return new ResponseDto<>(status, message, data);
  }

  public static ResponseDto<Void> fail(int status, String message) {
    return new ResponseDto<>(status, message, null);
  }



}
