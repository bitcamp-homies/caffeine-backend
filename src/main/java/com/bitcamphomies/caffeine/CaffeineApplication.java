package com.bitcamphomies.caffeine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"cafe.*"})
@EntityScan(basePackages = {"cafe.bean"})
@EnableJpaRepositories("cafe.repository")
public class CaffeineApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaffeineApplication.class, args);
	}

}
