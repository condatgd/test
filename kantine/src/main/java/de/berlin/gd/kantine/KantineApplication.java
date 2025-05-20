package de.berlin.gd.kantine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class KantineApplication {

	public static void main(String[] args) {
		SpringApplication.run(KantineApplication.class, args);
	}

}
