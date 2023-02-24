package com.winson121.springboot.demo.dogbreedapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DogbreedappApplication {

	public static void main(String[] args) {
		SpringApplication.run(DogbreedappApplication.class, args);
	}
	
	// define bean for RestTemplate to make client REST calls
	@Bean
    public RestTemplate getRestTemplate() {
		return new RestTemplate();
    }
}
