package org.dev.test.coffeenearby;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.dev.test.coffeenearby.mappers"})
public class CoffeeNearbyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeNearbyApplication.class, args);
	}

}
