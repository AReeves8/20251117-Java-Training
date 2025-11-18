package com.skillstorm.rabbitmqpublisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RabbitmqPublisherApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqPublisherApplication.class, args);
	}

}
