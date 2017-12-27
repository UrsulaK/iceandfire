package iceandfire.de.service;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 
 * @author UK
 * Spring Application Class
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class IceAndFireApplication {

	public static void main(String[] args) {
		SpringApplication.run(IceAndFireApplication.class, args);
	}
}
