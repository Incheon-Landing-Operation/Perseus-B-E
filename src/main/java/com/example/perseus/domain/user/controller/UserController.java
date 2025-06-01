package com.example.perseus.domain.user.controller;

import com.example.perseus.domain.user.dto.request.AdditionalInfoRequest;
import com.example.perseus.domain.user.dto.response.UserResponse;
import com.example.perseus.domain.user.entity.User;
import com.example.perseus.domain.user.facade.UserFacade;
import com.example.perseus.domain.user.service.UserService;
import com.example.perseus.global.annotation.CurrentUser;
import com.example.perseus.global.dto.ResponseDto;
import com.example.perseus.global.util.ApiUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
  private final UserService userService;

  @PostMapping("/add")
  public ResponseEntity<ResponseDto<Void>> addAdditionalInfo(@Valid @RequestBody final AdditionalInfoRequest additionalInfoRequest, @CurrentUser final User user) {
    userService.addAdditionalInfo(additionalInfoRequest, user);
    ResponseDto<Void> responseDto = ApiUtil.success(200, "추가 정보 입력 성공", null);
    return ResponseEntity.ok(responseDto);
  }

  @GetMapping
  public ResponseEntity<ResponseDto<UserResponse>> profile(@CurrentUser final User user) {
    UserResponse userResponse = userService.profile(user);
    ResponseDto<UserResponse> responseDto = ApiUtil.success(200, "프로필 조회 성공", userResponse);
    return ResponseEntity.ok(responseDto);
  }

}
