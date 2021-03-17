package com.dtu.weightliftingtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class WeightliftingTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeightliftingTrackerApplication.class, args);
	}

}
