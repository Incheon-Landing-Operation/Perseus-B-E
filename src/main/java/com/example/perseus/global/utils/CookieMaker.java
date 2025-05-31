package com.example.perseus.global.utils;

import jakarta.servlet.http.Cookie;

public class CookieMaker {
  public static Cookie makeCookie(String key, String value) {
    Cookie cookie = new Cookie(key, value);
    cookie.setMaxAge(1800);
    cookie.setHttpOnly(true);
    return cookie;
  }
}
