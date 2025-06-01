package com.example.perseus.domain.fact.entity;

import com.example.perseus.domain.fact.entity.type.Sentiment;
import com.example.perseus.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Getter
public class Fact {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long factId;
  @OneToOne
  @JoinColumn(name="writerId")
  private User writer;
  private String content;
  @Enumerated(EnumType.STRING)
  private Sentiment sentiment;
  @CreatedDate
  private LocalDateTime createdAt;
}
