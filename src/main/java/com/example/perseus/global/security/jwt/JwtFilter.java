package com.example.perseus.global.security.jwt;

import com.example.perseus.global.dto.ResponseDto;
import com.example.perseus.global.util.ApiUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
  private final JwtProvider jwtProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try {
      String token = jwtProvider.resolveToken(request);
      if (token != null) {
        Authentication authentication = jwtProvider.authorization(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
      filterChain.doFilter(request, response);
    } catch (ExpiredJwtException e) {
      response.setStatus(403);
      response.setContentType("application/json");
      ResponseDto<Void> responseDto = ApiUtil.fail(403, "access token 시간 만료");
      response.getWriter().write(
              new ObjectMapper().writeValueAsString(responseDto)
      );
    }
  }
}
