package com.example.perseus.global.security.auth.oauth.user;

import com.example.perseus.domain.user.entity.SocialType;
import com.example.perseus.domain.user.entity.User;
import com.example.perseus.domain.user.entity.UserRole;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class OAuthAttributes {

  private String nameAttributeKey;
  private OAuth2UserInfo oauth2UserInfo;

  @Builder
  private OAuthAttributes(String nameAttributeKey, OAuth2UserInfo oauth2UserInfo) {
    this.nameAttributeKey = nameAttributeKey;
    this.oauth2UserInfo = oauth2UserInfo;
  }

  public static OAuthAttributes of(SocialType socialType,
                                   String userNameAttributeKey, Map<String, Object> attributes) {
    if(socialType == SocialType.GOOGLE) {
      return ofGoogle(userNameAttributeKey, attributes);
    }
    return null;
  }

  private static OAuthAttributes ofGoogle(String userNameAttributeKey, Map<String, Object> attributes) {
    return OAuthAttributes.builder()
            .nameAttributeKey(userNameAttributeKey)
            .oauth2UserInfo(new GoogleOAuth2UserInfo(attributes))
            .build();
  }

  public User toEntity(SocialType socialType, OAuth2UserInfo oauth2UserInfo) {
    return User.builder()
            .socialType(socialType)
            .socialId(oauth2UserInfo.getId())
            .email(UUID.randomUUID() + "@socialUser.com")
            .name(oauth2UserInfo.getNickName())
            .imageUrl(oauth2UserInfo.getImageUrl())
            .role(UserRole.GUEST)
            .build();
  }

}
