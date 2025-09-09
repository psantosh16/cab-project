package com.pmo.cab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CabApplication {

	public static void main(String[] args) {
		SpringApplication.run(CabApplication.class, args);
	}

}
