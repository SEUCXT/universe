package com.seu.universe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class UniverseApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniverseApplication.class, args);
	}
}
