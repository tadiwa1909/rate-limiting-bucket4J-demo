package com.example.rate_limiting_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class RateLimitingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RateLimitingDemoApplication.class, args);
	}

}
