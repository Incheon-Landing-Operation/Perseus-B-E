package com.example.perseus.global.util;

import com.example.perseus.global.dto.ResponseDto;

public class ApiUtil {

  public static <T> ResponseDto<T> responseDto(int status, String message, T data) {
    return new ResponseDto<>(status, message, data);
  }

}
