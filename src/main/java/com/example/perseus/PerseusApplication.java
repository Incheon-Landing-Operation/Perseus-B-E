package com.example.perseus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableFeignClients(basePackages = "com.example.perseus.global.feign")
@EnableJpaAuditing
public class PerseusApplication {

  public static void main(String[] args) {
    SpringApplication.run(PerseusApplication.class, args);
  }

}
