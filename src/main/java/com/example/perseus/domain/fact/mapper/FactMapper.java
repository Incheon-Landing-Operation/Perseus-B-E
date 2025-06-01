package com.example.perseus.domain.fact.mapper;

import com.example.perseus.domain.fact.dto.response.FactResponse;
import com.example.perseus.domain.fact.entity.Fact;
import com.example.perseus.domain.fact.entity.type.Sentiment;
import com.example.perseus.domain.user.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FactMapper {

  public Fact toFact(String content, Sentiment sentiment, User writer) {
    return Fact.builder()
            .content(content)
            .writer(writer)
            .sentiment(sentiment)
            .build();
  }

  public FactResponse toFactResponse(Fact createdFact) {
    Long factId = createdFact.getFactId();
    User writer = createdFact.getWriter();
    String content = createdFact.getContent();
    LocalDateTime createdAt = createdFact.getCreatedAt();

    return new FactResponse(factId, writer, content, createdAt);
  }
}
