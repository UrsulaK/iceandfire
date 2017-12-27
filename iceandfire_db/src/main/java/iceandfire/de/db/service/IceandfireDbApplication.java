package iceandfire.de.db.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class IceandfireDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(IceandfireDbApplication.class, args);
	}
}
