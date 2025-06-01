package com.example.perseus.domain.user.service;

import com.example.perseus.domain.user.dto.request.AdditionalInfoRequest;
import com.example.perseus.domain.user.entity.User;
import com.example.perseus.domain.user.facade.UserFacade;
import com.example.perseus.domain.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  @Transactional
  public void addAdditionalInfo(final AdditionalInfoRequest additionalInfoRequest, final User user) {
    user.addInfo(additionalInfoRequest);
    user.sign();
    userRepository.save(user);
  }
}
