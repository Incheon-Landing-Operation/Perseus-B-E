package com.example.perseus.global.security.auth.oauth.user;

import java.util.Map;

public abstract class OAuth2UserInfo {
  protected Map<String, Object> attributes;

  public OAuth2UserInfo(Map<String, Object> attributes) {
    this.attributes = attributes;
  }

  abstract String getId();
  abstract String getNickName();
  abstract String getImageUrl();
}
