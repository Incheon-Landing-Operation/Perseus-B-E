package com.example.perseus.global.security.auth.oauth.user;

import java.util.Map;

public class GoogleOAuth2UserInfo extends OAuth2UserInfo {

  public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
    super(attributes);
  }

  @Override
  String getId() {
    return (String) attributes.get("sub");
  }

  @Override
  String getNickName() {
    return (String) attributes.get("name");
  }

  @Override
  String getImageUrl() {
    return (String) attributes.get("picture");
  }
}
