package com.example.perseus.domain.constellation.repository;

import com.example.perseus.domain.constellation.entity.Constellation;

import java.util.List;

public interface ConstellationJDBCRepository {
  void saveConstellationList(List<Constellation> constellationList);
}

