package com.example.perseus.domain.factUpdate.controller;

import com.example.perseus.domain.factUpdate.service.FactUpdateService;
import com.example.perseus.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/factUpdate")
public class FactUpdateController {
  private final FactUpdateService factUpdateService;

//  @PostMapping
//  public ResponseEntity<ResponseDto<Void>>
}
