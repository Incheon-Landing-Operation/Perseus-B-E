package com.example.perseus.global.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class CookieUtil {
  public static Cookie makeCookie(final String key, final String value) {
    Cookie cookie = new Cookie(key, value);
    cookie.setMaxAge(1800);
    cookie.setHttpOnly(true);
    return cookie;
  }

  public static String extract(final HttpServletRequest request, final String cookieName) {
    if (request.getCookies() == null) return null;

    for (Cookie cookie : request.getCookies()) {
      if (cookieName.equals(cookie.getName())) {
        return cookie.getValue();
      }
    }
    return null;
  }
}
