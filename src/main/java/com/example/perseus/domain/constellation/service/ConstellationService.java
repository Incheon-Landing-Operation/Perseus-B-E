package com.example.perseus.domain.constellation.service;

import com.example.perseus.domain.constellation.entity.Constellation;
import com.example.perseus.domain.constellation.mapper.ConstellationMapper;
import com.example.perseus.domain.constellation.repository.ConstellationRepository;
import com.example.perseus.domain.fact.entity.Fact;
import com.example.perseus.domain.fact.service.FactService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ConstellationService {
  private final ConstellationRepository constellationRepository;
  private final FactService factService;
  private final ConstellationMapper constellationMapper;

  @Scheduled(cron = "0 0 12 * * *")
  @Transactional
  public void saveConstellation() {
    LocalDateTime threshold = LocalDateTime.now().minusDays(3);

    Set<Long> existingFactIds = constellationRepository.
            findAllFactIds();

    List<Constellation> constellationList = factService.findByCreatedAtBefore(threshold)
            .stream()
            .filter(fact -> !existingFactIds.contains(fact.getFactId()))
            .map(constellationMapper::toConstellation)
            .toList();

    constellationRepository.saveConstellationList(constellationList);
  }

}
