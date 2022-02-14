package com.library.management.eurekaNamingServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaNamingServerApplication {

	public static void main(String[] args) {

		System.out.println("Eureka Starting..");
		SpringApplication.run(EurekaNamingServerApplication.class, args);
	}

}
