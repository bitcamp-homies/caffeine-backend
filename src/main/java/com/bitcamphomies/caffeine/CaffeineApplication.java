package com.bitcamphomies.caffeine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = { "cafe.*" })
@EntityScan(basePackages = { "cafe.bean" })
@EnableJpaRepositories("cafe.repository")
@Configuration
@MapperScan("cafe.repository.mybatis")
public class CaffeineApplication {

  public static void main(String[] args) {
    SpringApplication.run(CaffeineApplication.class, args);
  }

  // CORS 이슈 해결
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
      }
    };
  }

}
