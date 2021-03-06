package iceandfire.de.frontend.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class IceandfireFrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(IceandfireFrontendApplication.class, args);
	}
}
