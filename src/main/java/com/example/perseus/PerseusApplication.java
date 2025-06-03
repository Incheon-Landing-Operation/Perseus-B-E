package com.example.perseus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableFeignClients(basePackages = "com.example.perseus.global.feign")
@EnableJpaAuditing
@EnableScheduling
public class PerseusApplication {

  public static void main(String[] args) {
    SpringApplication.run(PerseusApplication.class, args);
  }

}
