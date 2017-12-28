package iceandfire.de.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class IceAndFireServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IceAndFireServiceApplication.class, args);
	}
}
