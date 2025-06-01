package com.example.perseus.global.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUtil {
  public static String getCurrentEmail() {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    return email;
  }
}
