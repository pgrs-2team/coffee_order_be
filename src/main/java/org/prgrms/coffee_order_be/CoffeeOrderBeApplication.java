package org.prgrms.coffee_order_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CoffeeOrderBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeOrderBeApplication.class, args);
	}

}
