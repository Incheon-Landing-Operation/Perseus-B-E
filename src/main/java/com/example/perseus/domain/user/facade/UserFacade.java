package com.example.perseus.domain.user.facade;

import com.example.perseus.domain.user.entity.User;
import com.example.perseus.domain.user.exception.NotFoundUserException;
import com.example.perseus.domain.user.repository.UserRepository;
import com.example.perseus.global.util.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {
  private final UserRepository userRepository;

  public User getCurrentUser() {
    String email = AuthenticationUtil.getCurrentEmail();
    User user = userRepository.findByEmail(email)
            .orElseThrow(NotFoundUserException::getInstance);
    return user;
  }
}
