package com.example.perseus.domain.fact.controller;

import com.example.perseus.domain.fact.dto.request.FactRequest;
import com.example.perseus.domain.fact.dto.response.FactResponse;
import com.example.perseus.domain.fact.service.FactService;
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
@RequestMapping(value="/fact")
public class FactController {
  private final FactService factService;

  @PostMapping
  public ResponseEntity<ResponseDto<FactResponse>> write(@Valid @RequestBody final FactRequest request, @CurrentUser final User writer) {
    FactResponse factResponse = factService.write(request, writer);
    ResponseDto<FactResponse> responseDto = ApiUtil.success(200, "사실 작성 성공", factResponse);
    return ResponseEntity.ok(responseDto);
  }

  @DeleteMapping("/{fact-id}")
  public ResponseEntity<ResponseDto<Void>> delete(@PathVariable("fact-id") final Long factId) {
    factService.delete(factId);
    ResponseDto<Void> responseDto = ApiUtil.success(200, "사실 제거 성공", null);
    return ResponseEntity.ok(responseDto);
  }

}
