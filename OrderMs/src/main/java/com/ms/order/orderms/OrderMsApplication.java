package com.ms.order.orderms;

import java.util.Currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories
public class OrderMsApplication {


	@Autowired
	OrderRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(OrderMsApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			ProductInventory p = new ProductInventory(2, "test order");
			repo.save(p);
			
			
			

		};
	}

}
