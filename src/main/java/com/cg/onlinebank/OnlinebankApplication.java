package com.cg.onlinebank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class OnlinebankApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinebankApplication.class, args);
	}

}
