package org.hcv.chodoido_ute_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ChodoidoUteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChodoidoUteServiceApplication.class, args);
	}

}
