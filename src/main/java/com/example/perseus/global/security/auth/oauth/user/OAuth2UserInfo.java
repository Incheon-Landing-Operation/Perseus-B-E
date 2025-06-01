package com.example.perseus.global.security.auth.oauth.user;

import java.util.Map;

public abstract class OAuth2UserInfo {
  protected Map<String, Object> attributes;

  public OAuth2UserInfo(Map<String, Object> attributes) {
    this.attributes = attributes;
  }

  public abstract String getId();
  public abstract String getNickName();
  public abstract String getImageUrl();
}
