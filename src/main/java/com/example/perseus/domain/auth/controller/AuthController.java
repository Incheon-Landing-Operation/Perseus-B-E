package com.example.perseus.domain.auth.controller;

import com.example.perseus.domain.auth.dto.request.RefreshTokenRequest;
import com.example.perseus.domain.auth.dto.response.TokenResponse;
import com.example.perseus.domain.auth.service.AuthService;
import com.example.perseus.global.dto.ResponseDto;
import com.example.perseus.global.util.ApiUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/auth")
public class AuthController {
  private final AuthService authService;

  // reissue
  @PostMapping("/reissue")
  public ResponseEntity<ResponseDto<TokenResponse>> reissue(@Valid @RequestBody final RefreshTokenRequest refreshTokenRequest) {
    TokenResponse tokenResponse = authService.reissue(refreshTokenRequest.refreshToken());
    ResponseDto<TokenResponse> responseDto = ApiUtil.success(200, "재발급 성공", tokenResponse);
    return ResponseEntity.ok(responseDto);
  }

  // logout
  @PostMapping("/logout")
  public ResponseEntity<ResponseDto<Void>> logout(@Valid @RequestBody final RefreshTokenRequest refreshTokenRequest) {
    authService.logout(refreshTokenRequest.refreshToken());
    ResponseDto<Void> responseDto = ApiUtil.success(200, "로그아웃 성공", null);
    return ResponseEntity.ok(responseDto);
  }


}
