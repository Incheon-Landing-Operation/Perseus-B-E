package com.example.perseus.domain.factUpdate.repository;

import com.example.perseus.domain.factUpdate.entity.FactUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactUpdateRepository extends JpaRepository<FactUpdate, Long> {
  List<FactUpdate> findAllByTargetFactId(Long factId);
}
