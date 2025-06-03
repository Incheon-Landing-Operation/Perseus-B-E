package com.example.perseus.domain.factUpdate.mapper;

import com.example.perseus.domain.fact.entity.Fact;
import com.example.perseus.domain.factUpdate.dto.response.FactUpdateResponse;
import com.example.perseus.domain.factUpdate.entity.FactUpdate;
import com.example.perseus.domain.factUpdate.entity.type.CognitiveDistortion;
import com.example.perseus.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class FactUpdateMapper {

  public FactUpdate toFactUpdate(Fact target, String cognitiveDistortion, String content, User writer) {
    return FactUpdate.of(writer, target, CognitiveDistortion.valueOf(cognitiveDistortion), content);
  }

  public FactUpdateResponse toFactUpdateResponse(FactUpdate factUpdate) {
    return FactUpdateResponse.builder()
            .factUpdateId(factUpdate.getFactUpdateId())
            .writer(factUpdate.getWriter())
            .createdAt(factUpdate.getCreatedAt())
            .content(factUpdate.getContent())
            .cognitive_distortion(factUpdate.getCognitive_distortion())
            .build();
  }
}
