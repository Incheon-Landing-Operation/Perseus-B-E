package com.example.perseus.domain.factUpdate.service;

import com.example.perseus.domain.fact.entity.Fact;
import com.example.perseus.domain.fact.service.FactService;
import com.example.perseus.domain.factUpdate.dto.request.FactUpdateRequest;
import com.example.perseus.domain.factUpdate.dto.response.FactUpdateResponse;
import com.example.perseus.domain.factUpdate.entity.FactUpdate;
import com.example.perseus.domain.factUpdate.exception.NotFoundFactUpdateException;
import com.example.perseus.domain.factUpdate.mapper.FactUpdateMapper;
import com.example.perseus.domain.factUpdate.repository.FactUpdateRepository;
import com.example.perseus.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FactUpdateService {
  private final FactUpdateRepository factUpdateRepository;
  private final FactUpdateMapper factUpdateMapper;
  private final FactService factService;

  @Transactional
  public void write(FactUpdateRequest factUpdateRequest, User writer) {
    Long factId = factUpdateRequest.targetId();
    Fact fact = factService.findById(factId);
    String cognitive_distortion = factUpdateRequest.cognitive_distortion();
    String content = factUpdateRequest.content();

    FactUpdate factUpdate = factUpdateMapper.toFactUpdate(fact, cognitive_distortion, content, writer);
    factUpdateRepository.save(factUpdate);
  }

  @Transactional
  public void delete(Long factUpdateId) {
    factUpdateRepository.deleteById(factUpdateId);
  }

  public List<FactUpdateResponse> findAllByFactId(Long factId) {
    List<FactUpdateResponse> factUpdateResponseList = factUpdateRepository.findAllByTargetFactId(factId)
            .stream()
            .map(factUpdateMapper::toFactUpdateResponse)
            .toList();
    return factUpdateResponseList;
  }
}
