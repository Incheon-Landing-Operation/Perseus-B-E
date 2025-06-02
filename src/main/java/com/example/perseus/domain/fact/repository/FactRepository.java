package com.example.perseus.domain.fact.repository;

import com.example.perseus.domain.fact.entity.Fact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FactRepository extends JpaRepository<Fact, Long> {
  List<Fact> findByCreatedAtBefore(LocalDateTime threshold);
}
