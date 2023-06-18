package com.cloudtechies.orchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.cloudtechies.orchestrator"})
@EnableScheduling
public class PayloadOrchestratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayloadOrchestratorApplication.class, args);
	}

}
