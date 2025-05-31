package com.example.perseus.global.util;

import jakarta.servlet.http.Cookie;

public class CookieUtil {
  public static Cookie makeCookie(String key, String value) {
    Cookie cookie = new Cookie(key, value);
    cookie.setMaxAge(1800);
    cookie.setHttpOnly(true);
    return cookie;
  }
}
