package com.example.perseus.domain.factUpdate.entity;

import com.example.perseus.domain.fact.entity.Fact;
import com.example.perseus.domain.factUpdate.entity.type.CognitiveDistortion;
import com.example.perseus.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class FactUpdate {
  @Id
  private Long factUpdateId;
  @OneToOne
  @JoinColumn(name="writerId")
  private User writer;
  @ManyToOne
  @JoinColumn(name="factId")
  private Fact target;
  @Enumerated(EnumType.STRING)
  private CognitiveDistortion cognitive_distortion;
  private String content;
  @CreatedDate
  private LocalDateTime createdAt;

}
