package com.example.perseus.global.security.auth.oauth.handler;

import com.example.perseus.global.dto.ResponseDto;
import com.example.perseus.global.util.ApiUtil;
import com.example.perseus.global.util.HttpServletResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
public class OAuth2LoginFailureHandler implements AuthenticationFailureHandler {
  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    HttpServletResponseUtil.setting(response, 400);

    ResponseDto<Void> responseDto = ApiUtil.fail(400, "소셜 로그인 실패");
    response.getWriter().write(new ObjectMapper().writeValueAsString(responseDto));
    log.error("소셜 로그인 실패!!");
    log.error("에러 메시지 : " + exception.getMessage());
  }
}
