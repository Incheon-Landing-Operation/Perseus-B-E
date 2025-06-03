package com.example.perseus.domain.constellation.controller;

import com.example.perseus.domain.constellation.service.ConstellationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/constellation")
public class ConstellationController {
  private final ConstellationService constellationService;

}
