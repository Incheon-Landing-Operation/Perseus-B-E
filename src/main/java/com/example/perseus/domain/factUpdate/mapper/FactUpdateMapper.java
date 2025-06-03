package com.example.perseus.domain.factUpdate.mapper;

import com.example.perseus.domain.fact.entity.Fact;
import com.example.perseus.domain.factUpdate.entity.FactUpdate;
import com.example.perseus.domain.factUpdate.entity.type.CognitiveDistortion;
import com.example.perseus.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class FactUpdateMapper {

  public FactUpdate toFactUpdate(Fact target, String cognitiveDistortion, String content, User writer) {
    return FactUpdate.of(writer, target, CognitiveDistortion.valueOf(cognitiveDistortion), content);
  }
}
