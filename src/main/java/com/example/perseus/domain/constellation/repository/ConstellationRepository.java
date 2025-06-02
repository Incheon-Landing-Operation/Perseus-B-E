package com.example.perseus.domain.constellation.repository;
import com.example.perseus.domain.constellation.entity.Constellation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ConstellationRepository extends JpaRepository<Constellation, Long>, ConstellationJDBCRepository {

  @Query("SELECT distinct c.alpha.factId FROM Constellation c")
  Set<Long> findAllFactIds();
}
