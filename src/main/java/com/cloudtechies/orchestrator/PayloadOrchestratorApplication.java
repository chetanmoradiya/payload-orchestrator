package com.cloudtechies.orchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.cloudtechies.orchestrator"})
public class PayloadOrchestratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayloadOrchestratorApplication.class, args);
	}

}
