package com.example.perseus.domain.auth.service;

import com.example.perseus.domain.auth.dto.response.LoginUrl;

public interface AuthLinkUseCase {
  LoginUrl getUrl();
}
