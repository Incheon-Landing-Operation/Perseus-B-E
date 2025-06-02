package com.example.perseus.domain.constellation.entity;

import com.example.perseus.domain.fact.entity.Fact;
import com.example.perseus.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Constellation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long constellationId;
  @ManyToOne
  @JoinColumn(name="ownerId")
  private User owner;
  @OneToOne
  @JoinColumn(name="alphaId")
  private Fact alpha;

  public static Constellation of(User writer, Fact fact) {
    return Constellation.builder()
            .owner(writer)
            .alpha(fact)
            .build();
  }
}
