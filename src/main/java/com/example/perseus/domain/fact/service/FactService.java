package com.example.perseus.domain.fact.service;

import com.example.perseus.domain.fact.dto.response.FactResponse;
import com.example.perseus.domain.fact.dto.request.FactRequest;
import com.example.perseus.domain.fact.entity.Fact;
import com.example.perseus.domain.fact.entity.type.Sentiment;
import com.example.perseus.domain.fact.exception.NotFoundFactException;
import com.example.perseus.domain.fact.mapper.FactMapper;
import com.example.perseus.domain.fact.repository.FactRepository;
import com.example.perseus.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FactService {
  private final FactRepository factRepository;
  private final FactMapper factMapper;

  @Transactional
  public FactResponse write(final FactRequest request, final User writer) {
    String content = request.content();
    String sentiment = request.sentiment();
    Double latitude = request.latitude();
    Double longitude = request.longitude();

    Fact fact = factMapper.toFact(content, sentiment, writer, latitude, longitude);
    Fact createdFact = factRepository.save(fact);

    FactResponse factResponse = factMapper.toFactResponse(createdFact);
    return factResponse;
  }

  @Transactional
  public void delete(Long factId) {
    factRepository.deleteById(factId);
  }

  public Fact findById(Long factId) {
    return factRepository.findById(factId)
            .orElseThrow(NotFoundFactException::getInstance);
  }



  public List<Fact> findByCreatedAtBefore(LocalDateTime threshold) {
    return factRepository.findByCreatedAtBefore(threshold);
  }
}
