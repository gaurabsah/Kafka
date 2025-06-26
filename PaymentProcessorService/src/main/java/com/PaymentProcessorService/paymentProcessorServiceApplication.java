package com.PaymentProcessorService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class paymentProcessorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(paymentProcessorServiceApplication.class, args);
	}

}
