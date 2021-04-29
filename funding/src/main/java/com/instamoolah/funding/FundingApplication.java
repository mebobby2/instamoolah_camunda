package com.instamoolah.funding;

import org.springframework.context.event.EventListener;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.boot.SpringApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.instamoolah") // So it can packages inside the reactive module as well, which is a library
@EnableProcessApplication
public class FundingApplication {
	@Autowired
  private RuntimeService runtimeService;

	public static void main(String[] args) {
		SpringApplication.run(FundingApplication.class, args);
	}

	@EventListener
  private void processPostDeploy(PostDeployEvent event) {
		System.out.println("Camunda Process Engine deployed successfully");
  }
}
