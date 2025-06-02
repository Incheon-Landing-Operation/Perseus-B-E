package com.example.perseus.domain.constellation.repository;

import com.example.perseus.domain.constellation.entity.Constellation;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConstellationJDBCRepositoryImpl implements ConstellationJDBCRepository {
    private final JdbcTemplate jdbcTemplate;

    public void saveConstellationList(List<Constellation> constellationList) {
        String sql = "INSERT INTO constellation (owner_id, alpha_id) VALUES (?, ?)";
        
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Constellation constellation = constellationList.get(i);
                ps.setLong(1, constellation.getOwner().getUserId());
                ps.setLong(2, constellation.getAlpha().getFactId());
            }

            @Override
            public int getBatchSize() {
                return constellationList.size();
            }
        });
    }
}