package com.example.perseus.domain.factUpdate.repository;

import com.example.perseus.domain.factUpdate.entity.FactUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactUpdateRepository extends JpaRepository<FactUpdate, Long> {
}
