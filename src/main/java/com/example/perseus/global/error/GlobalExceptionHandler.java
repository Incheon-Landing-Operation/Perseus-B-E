package com.example.perseus.global.error;

import com.example.perseus.global.dto.ResponseDto;
import com.example.perseus.global.error.exception.BaseException;
import com.example.perseus.global.error.exception.ErrorCode;
import com.example.perseus.global.util.ApiUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BaseException.class)
  public ResponseEntity<ResponseDto<Void>> baseExceptionHandler(BaseException e) {
    ErrorCode errorCode = e.getErrorCode();
    int status = errorCode.getStatus();
    String errorMessage = errorCode.getMessage();
    log.error(errorMessage);

    ResponseDto<Void> error = ApiUtil.fail(status, errorMessage);
    return ResponseEntity.status(status)
            .body(error);
  }
}
