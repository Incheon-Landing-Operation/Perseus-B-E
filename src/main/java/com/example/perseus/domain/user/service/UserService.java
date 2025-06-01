package com.example.perseus.domain.user.service;

import com.example.perseus.domain.user.dto.request.AdditionalInfoRequest;
import com.example.perseus.domain.user.dto.response.UserResponse;
import com.example.perseus.domain.user.entity.User;
import com.example.perseus.domain.user.mapper.UserMapper;
import com.example.perseus.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Transactional
  public void addAdditionalInfo(final AdditionalInfoRequest additionalInfoRequest, final User user) {
    user.addInfo(additionalInfoRequest);
    user.sign();
    userRepository.save(user);
  }

  public UserResponse profile(final User user) {
    UserResponse userResponse = userMapper.toUserResponse(user);
    return userResponse;
  }

  @Transactional
  public User login(String email, String name, String imageUrl) {
    User user = userRepository.findByEmail(email)
            .orElseGet(() -> this.signUp(email, name, imageUrl));
    return user;
  }
  private User signUp(String email, String name, String imageUrl) {
    User user = userMapper.toUser(email, name, imageUrl);
    userRepository.save(user);
    return user;
  }
}
