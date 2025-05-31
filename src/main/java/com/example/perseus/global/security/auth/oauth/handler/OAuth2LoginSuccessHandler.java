package com.example.perseus.global.security.auth.oauth.handler;

import com.example.perseus.domain.user.entity.UserRole;
import com.example.perseus.global.security.auth.jwt.JwtProvider;
import com.example.perseus.global.security.auth.oauth.user.CustomOAuth2User;
import com.example.perseus.global.util.CookieUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
  private final JwtProvider jwtProvider;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
    log.info("소셜 로그인 성공");
    CustomOAuth2User principal = (CustomOAuth2User) authentication.getPrincipal();
    String email = principal.getEmail();
    UserRole role = principal.getRole();

    String accessToken = jwtProvider.createAccessToken(email, role);
    String refreshToken = jwtProvider.createRefreshToken(email, role);

    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    response.addCookie(CookieUtil.makeCookie("refresh", refreshToken));

    Map<String, String> body = new ConcurrentHashMap<>();
    body.put("accessToken", accessToken);
    body.put("status", role == UserRole.GUEST ? "GUEST" : "USER");
    response.getWriter().write(new ObjectMapper().writeValueAsString(body));
  }
}
