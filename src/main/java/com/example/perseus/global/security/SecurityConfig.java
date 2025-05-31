package com.example.perseus.global.security;


import com.example.perseus.global.security.auth.jwt.JwtFilter;
import com.example.perseus.global.security.auth.oauth.CustomOAuth2UserService;
import com.example.perseus.global.security.auth.oauth.OAuth2LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
  private final CustomOAuth2UserService customOAuth2UserService;
  private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

  private final JwtFilter jwtFilter;
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

    http
            .oauth2Login(oauth -> oauth
                    .userInfoEndpoint(user -> user.userService(customOAuth2UserService))
                    .successHandler(oAuth2LoginSuccessHandler));

    http
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
