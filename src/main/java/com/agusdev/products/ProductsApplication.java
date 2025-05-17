package com.agusdev.products;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class ProductsApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();
		System.setProperty("SPRING_DATASOURCE_URL", dotenv.get("SPRING_DATASOURCE_URL"));
		System.setProperty("SPRING_DATASOURCE_USERNAME", dotenv.get("SPRING_DATASOURCE_USERNAME"));
		System.setProperty("SPRING_DATASOURCE_PASSWORD", dotenv.get("SPRING_DATASOURCE_PASSWORD"));
		System.setProperty("SPRING_DATASOURCE_DRIVER_CLASS_NAME", dotenv.get("SPRING_DATASOURCE_DRIVER_CLASS_NAME"));

		System.out.println(dotenv.get("SPRING_DATASOURCE_URL"));

		//variables para produccion
//		String datasourceUrl = System.getenv("SPRING_DATASOURCE_URL");
//		String datasourceUsername = System.getenv("SPRING_DATASOURCE_USERNAME");
//		String datasourcePassword = System.getenv("SPRING_DATASOURCE_PASSWORD");
//		String datasourceDriverClassName = System.getenv("SPRING_DATASOURCE_DRIVER_CLASS_NAME");
//
//
//		System.out.println("URL: " + datasourceUrl);
//		System.out.println("Username: " + datasourceUsername);
//		System.out.println("Password: " + datasourcePassword);
//		System.out.println("Driver Class: " + datasourceDriverClassName);



		SpringApplication.run(ProductsApplication.class, args);

	}

}
