package com.skillstorm.hellospringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.skillstorm.hellospringboot.beans.Vehicle;

/*
 * Combo of three other annotations:
 * 		@Configuration - specifies that a class will be a configuration class - more specifically, this will be a class that has beans that Spring needs to manage
 * 		@ComponentScan - searches your package for an class annotated with @Component and make beans for those components
 * 		@EnableAutoConfiguration - tells spring boot to auto-configure the app context with things we need (like a server for a webapp)
 */
@SpringBootApplication
public class HelloSpringBootApplication implements CommandLineRunner {


	@Autowired				// asks spring to give you a bean
	@Qualifier("camaro")	// tells spring which bean to give us
	private Vehicle car3;

	public static void main(String[] args) {

		/*
		 * APPLICATION CONTEXT
		 * 		starts up your IoC container
		 * 		where your beans live
		 */
		ApplicationContext context = SpringApplication.run(HelloSpringBootApplication.class, args);

		Vehicle car = (Vehicle) context.getBean("tesla");
		System.out.println("I'm driving my new tesla!! Woo!!");
		car.drive();

		Vehicle car2 = (Vehicle) context.getBean("tesla");
		System.out.println("Car1: " + car);
		System.out.println("Car2: " + car2);

	}


	/*
	 * COMMAND LINE RUNNER
	 * 		method that runs AFTER the app context is loaded
	 * 
	 * 		usually used to perform some setup for an application - like loading in data
	 * 
	 * 		functional interface - so it could be a lambda if you wanted
	 */
	@Override
	public void run(String... args) throws Exception {
		System.out.println(car3);
		System.out.println("Im diving my new camaro!!!");
		car3.drive();
	}

}
