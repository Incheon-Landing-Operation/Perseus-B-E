package com.example.perseus.domain.fact.mapper;

import com.example.perseus.domain.fact.dto.response.FactResponse;
import com.example.perseus.domain.fact.entity.Fact;
import com.example.perseus.domain.fact.entity.type.Sentiment;
import com.example.perseus.domain.user.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FactMapper {

  public Fact toFact(final String content, final String sentiment, final User writer, final Double latitude, final Double longitude) {
    return Fact.of(content, writer, Sentiment.valueOf(sentiment), latitude, longitude);
  }

  public FactResponse toFactResponse(final Fact createdFact) {
    Long factId = createdFact.getFactId();
    User writer = createdFact.getWriter();

    String content = createdFact.getContent();
    LocalDateTime createdAt = createdFact.getCreatedAt();

    return new FactResponse(factId, writer, content, createdAt);
  }
}
