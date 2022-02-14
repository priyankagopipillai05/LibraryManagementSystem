package com.library.management.bookReservationService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class BookReservationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookReservationServiceApplication.class, args);
	}


	@LoadBalanced
	@Bean
	RestTemplate restTemplate(){
			return new RestTemplate();
	}
}
