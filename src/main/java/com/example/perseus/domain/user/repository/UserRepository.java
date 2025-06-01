package com.example.perseus.domain.user.repository;

import com.example.perseus.domain.user.entity.type.SocialType;
import com.example.perseus.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findBySocialTypeAndSocialId(SocialType socialType, String id);

  Optional<User> findByEmail(String email);
}
