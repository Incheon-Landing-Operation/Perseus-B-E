package com.example.perseus.domain.constellation.mapper;

import com.example.perseus.domain.constellation.entity.Constellation;
import com.example.perseus.domain.fact.entity.Fact;
import org.springframework.stereotype.Component;

@Component
public class ConstellationMapper {
  public Constellation toConstellation(Fact fact) {
    return Constellation.of(fact.getWriter(), fact);
  }
}
