package com.maxwell.myCollection;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyCollectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyCollectionApplication.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return (args) -> {

		};
	}

}
