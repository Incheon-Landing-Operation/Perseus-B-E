package com.example.perseus.global.util;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

public class HttpServletResponseUtil {
  public static HttpServletResponse setting(HttpServletResponse response, int status) {
    response.setStatus(status);
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    return response;
  }
}
