package com.skillstorm.rabbitmqsubscriber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RabbitmqSubscriberApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqSubscriberApplication.class, args);
	}

}
