package com.example.perseus.domain.constellation.repository;
import com.example.perseus.domain.constellation.entity.Constellation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ConstellationRepository extends JpaRepository<Constellation, Long>, ConstellationJDBCRepository {

  @Query("SELECT distinct c.alpha.factId FROM Constellation c")
  Set<Long> findAllFactIds();

  @Query(value = """
    SELECT * FROM constellation c
    WHERE 
      (6371 * acos(
        cos(radians(:lat)) * cos(radians(c.latitude)) *
        cos(radians(c.longitude) - radians(:lng)) +
        sin(radians(:lat)) * sin(radians(c.latitude))
      )) <= :radius
""", nativeQuery = true)
  List<Constellation> findAllWithinDistance(
          @Param("lat") double lat,
          @Param("lng") double lng,
          @Param("radius") double radius
  );


}
