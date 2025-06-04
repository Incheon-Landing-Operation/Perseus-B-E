package com.example.perseus.domain.auth.controller;

import com.example.perseus.domain.auth.dto.response.AccessToken;
import com.example.perseus.domain.auth.dto.response.LoginUrl;
import com.example.perseus.domain.auth.dto.response.TokenResponse;
import com.example.perseus.domain.auth.service.AuthService;
import com.example.perseus.domain.auth.dto.request.LoginRequest;
import com.example.perseus.domain.auth.dto.response.LoginResponse;
import com.example.perseus.domain.auth.service.GoogleAuthLinkUseCase;
import com.example.perseus.global.dto.ResponseDto;
import com.example.perseus.global.util.ApiUtil;
import com.example.perseus.global.util.CookieUtil;
import com.example.perseus.global.util.HttpServletResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/auth")
public class AuthController {
  private final AuthService authService;
  private final GoogleAuthLinkUseCase googleAuthLinkUseCase;


  @GetMapping("/google/url")
  public ResponseEntity<ResponseDto<LoginUrl>> getGoogleUrl() {
    LoginUrl loginUrl = googleAuthLinkUseCase.getUrl();
    ResponseDto<LoginUrl> responseDto = ApiUtil.success(200, "구글 로그인 url 조회", loginUrl);
    return ResponseEntity.ok(responseDto);
  }

  @PostMapping("/google/login")
  public ResponseEntity<ResponseDto<LoginResponse.WithOutRefreshToken>> login(@Valid @RequestBody final LoginRequest loginRequest, final HttpServletResponse response) {
    HttpServletResponseUtil.setting(response, 200);
    LoginResponse loginResponse = authService.login(loginRequest.token());
    response.addCookie(CookieUtil.makeCookie("refreshToken", loginResponse.refreshToken()));
    ResponseDto<LoginResponse.WithOutRefreshToken> responseDto = ApiUtil.success(200, "로그인 성공", loginResponse.withoutRefreshToken());
    return ResponseEntity.ok(responseDto);
  }

  // reissue
  @GetMapping("/reissue")
  public ResponseEntity<ResponseDto<AccessToken>> reissue(final HttpServletRequest request, final HttpServletResponse response) {
    String refreshToken = CookieUtil.extract(request, "refreshToken");
    TokenResponse tokenResponse = authService.reissue(refreshToken);

    response.addCookie(CookieUtil.makeCookie("refreshToken", tokenResponse.refreshToken()));
    ResponseDto<AccessToken> responseDto = ApiUtil.success(200, "재발급 성공", new AccessToken(tokenResponse.accessToken()));
    return ResponseEntity.ok(responseDto);
  }

  // logout
  @GetMapping("/logout")
  public ResponseEntity<ResponseDto<Void>> logout(final HttpServletRequest request) {
    String refreshToken = CookieUtil.extract(request, "refreshToken");
    authService.logout(refreshToken);

    ResponseDto<Void> responseDto = ApiUtil.success(200, "로그아웃 성공", null);
    return ResponseEntity.ok(responseDto);
  }


}
