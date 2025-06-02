package com.example.perseus.domain.factUpdate.controller;

import com.example.perseus.domain.factUpdate.dto.request.FactUpdateRequest;
import com.example.perseus.domain.factUpdate.service.FactUpdateService;
import com.example.perseus.domain.user.entity.User;
import com.example.perseus.global.annotation.CurrentUser;
import com.example.perseus.global.dto.ResponseDto;
import com.example.perseus.global.util.ApiUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/factUpdate")
public class FactUpdateController {
  private final FactUpdateService factUpdateService;

  @PostMapping
  public ResponseEntity<ResponseDto<Void>> write(@Valid @RequestBody final FactUpdateRequest factUpdateRequest, @CurrentUser final User writer) {
    factUpdateService.write(factUpdateRequest, writer);
    ResponseDto<Void> responseDto = ApiUtil.success(200, "인지 왜곡 수정 글 작성 성공", null);
    return ResponseEntity.ok(responseDto);
  }

  @DeleteMapping("/{fact-update-id}")
  public ResponseEntity<ResponseDto<Void>> delete(@PathVariable("fact-update-id") Long factUpdateId) {
    factUpdateService.delete(factUpdateId);
    ResponseDto<Void> responseDto = ApiUtil.success(200, "인지 왜곡 수정 글 삭제 성공", null);
    return ResponseEntity.ok(responseDto);
  }
}
