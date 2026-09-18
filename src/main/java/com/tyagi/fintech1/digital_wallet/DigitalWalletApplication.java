package com.tyagi.fintech1.digital_wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication(scanBasePackages = "com.tyagi.fintech1")
@EnableJpaRepositories(basePackages = "com.tyagi.fintech1.repository")
@EntityScan(basePackages = "com.tyagi.fintech1.entity")
public class DigitalWalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalWalletApplication.class, args);
	}

}
