package com.example.perseus.global.security.auth.oauth;

import com.example.perseus.domain.user.entity.UserRole;
import com.example.perseus.global.security.auth.jwt.JwtProvider;
import com.example.perseus.global.utils.CookieMaker;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
  private final JwtProvider jwtProvider;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    CustomOAuth2User principal = (CustomOAuth2User) authentication.getPrincipal();
    String email = principal.getEmail();
    UserRole role = principal.getRole();

    String accessToken = jwtProvider.createAccessToken(email, role);
    String refreshToken = jwtProvider.createRefreshToken(email, role);

    response.setHeader("access", accessToken);
    response.addCookie(CookieMaker.makeCookie("refresh", refreshToken));
  }

}
