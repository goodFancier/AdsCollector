package ru.businessculture.adscollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@PropertySource({
		"classpath:application.properties"
})
@EnableScheduling
public class AdsapiApplication {
	public static void main(String[] args) {
		SpringApplication.run(AdsapiApplication.class, args);
	}
}
