package com.example.perseus.domain.auth.service;

import com.example.perseus.domain.auth.dto.response.LoginUrl;
import com.example.perseus.global.config.properties.AuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class GoogleAuthLinkUseCase implements AuthLinkUseCase {

  private final AuthProperties authProperties;
  public LoginUrl getUrl() {
    String clientId = authProperties.clientId();
    String redirectUri = authProperties.redirectUri();

    String baseUrl = "https://accounts.google.com/o/oauth2/v2/auth";
    String responseType = "token";
    String scope = "https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile";

    String encodedScope = URLEncoder.encode(scope, StandardCharsets.UTF_8);

    String encodedRedirectUri = URLEncoder.encode(redirectUri, StandardCharsets.UTF_8);

    String url = baseUrl
            + "?client_id=" + clientId
            + "&redirect_uri=" + encodedRedirectUri
            + "&response_type=" + responseType
            + "&scope=" + encodedScope;
    return new LoginUrl(url);
  }
}
