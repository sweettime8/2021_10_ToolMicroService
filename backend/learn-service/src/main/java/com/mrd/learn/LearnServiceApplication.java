package com.mrd.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LearnServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnServiceApplication.class, args);
	}

}
